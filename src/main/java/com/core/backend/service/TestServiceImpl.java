package com.core.backend.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jxls.reader.ReaderConfig;
import org.jxls.reader.XLSReadMessage;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.core.backend.model.ParentModel;
import com.core.backend.repository.TestRepository;
import com.core.backend.util.JxlsReaderBuilder;
import com.core.backend.util.ServiceUtils;
import com.core.backend.util.JxlsDateConverter;
import com.monitorjbl.xlsx.StreamingReader;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


@Service
public class TestServiceImpl implements TestService {

	private static final String SWPML = "SWPML";
	private static final String SWPEND = "SWPEND";
	
	@Autowired
    private Environment env;

    
    public String propertyPml() {
        return env.getProperty("uri.cenace.pml");
    }
    
    public String propertyPend() {
        return env.getProperty("uri.cenace.pend");
    }   
    
    public String propertyFormat() {
        return env.getProperty("uri.cenace.format");
    }   
    
	@Autowired
	private TestRepository testRepository;

	@Override
	public Date getDateNow() {
		return this.testRepository.getDateNow();
	}

	@Override
	public String getNameSheets(MultipartFile file) throws IOException, InvalidFormatException {
		InputStream is = file.getInputStream();
		Workbook workbook = WorkbookFactory.create(is);
		System.out.println(workbook.getSheetName(0));
		System.out.println(workbook.getSheetName(1));
		System.out.println(workbook.getSheetName(2));
		return workbook.getSheetName(0) + "|" + workbook.getSheetName(1) + "|" + workbook.getSheetName(2);
	}

	@Override
	public void uploadBigFile(MultipartFile file) throws IOException {
		InputStream is = file.getInputStream();
		Workbook wb = StreamingReader.builder().rowCacheSize(100).bufferSize(8192).open(is);
		for (Sheet sheet : wb) {
			for (Row row : sheet) {
				for (Cell cell : row) {
					System.out.println(cell.getStringCellValue());
				}
			}
		}
	}

	@Override
	public List<ParentModel> uploadFileJxls(MultipartFile file) throws Exception {
		List<ParentModel> loadedData = new ArrayList<ParentModel>();
		List<XLSReadMessage> readMessages = new ArrayList<XLSReadMessage>();			
		InputStream is = file.getInputStream();
		byte[] xlsBytes = ServiceUtils.readFileFromStream(is);
		Workbook wb = WorkbookFactory.create(new ByteArrayInputStream(xlsBytes));
		List<String> sheetNames = new ArrayList<String>();	
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			sheetNames.add(wb.getSheetName(i));
		}		
		Map<Object, Object> beans = new HashMap<>();			
		XLSReader mainReader = JxlsReaderBuilder.build(sheetNames, beans);		
		List<Object> beanKeys = new ArrayList<Object>(beans.keySet());
		ReaderConfig.getInstance().setSkipErrors(true);
		ConvertUtils.register((Converter) new JxlsDateConverter(), Date.class);
		XLSReadStatus readStatus = mainReader.read(new ByteArrayInputStream(xlsBytes), beans);
		for (Object message : readStatus.getReadMessages()) {
			readMessages.add((XLSReadMessage) message);
		}
		for (Object key : beanKeys) {
			Object obj = beans.get(key);
			loadedData.add((ParentModel) obj);
		}		
		if (readMessages.size() <= 0) {
			System.out.println(readMessages.size());
		} else {
			System.out.println(readMessages.size());			
		}	
		return loadedData;
	}

	@Override
	public JSONObject getDataCenace(
			String system, 
			String process, 
			List<String> listNode, 
			String dateStart,
			String dateEnd,
			String typeNode) {
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();		
		headers.setContentType(MediaType.APPLICATION_JSON);		
		HttpEntity<Object> entity = new HttpEntity<>(headers);
		String response = null;
		String claveNodoOrZonacarga = null;
					
		String uriPml = null;
		List<String> listaNodos = new ArrayList<String>(); 			
		for(String item: listNode) {			
			String[] parts = item.split("_");
			String nodo = parts[0]; 
			listaNodos.add(nodo);
		}					
		if(typeNode.equals(SWPML)) {				
			uriPml = this.propertyPml();				
		} else if(typeNode.equals(SWPEND)) {				
			uriPml = this.propertyPend();			
		}			
		String format = this.propertyFormat();			
		String nodes = String.join(",", listaNodos);			
		String url = uriPml+"/"+system+"/"+process+"/"+nodes+"/"+dateStart+"/"+dateEnd+"/"+format;
					
		ResponseEntity<String> responseService = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		response = responseService.getBody();					    
	 
		String precio = null;
		JSONObject jsonObject = new JSONObject(response);
		JSONArray resultados = (JSONArray) jsonObject.get("Resultados");
		
		for(int i=0; i < resultados.length(); i++) {
			
			if(typeNode.equals(SWPEND)) {			    	
	    		claveNodoOrZonacarga = resultados.getJSONObject(i).getString("zona_carga");
	    		System.out.println(claveNodoOrZonacarga);
	    	} else if(typeNode.equals(SWPML)) {			    	
	    		claveNodoOrZonacarga = resultados.getJSONObject(i).getString("clv_nodo");
	    		System.out.println(claveNodoOrZonacarga);
	    	}
			
			JSONObject myResponse = resultados.getJSONObject(i);        
	        JSONArray valores = (JSONArray) myResponse.get("Valores");	        
	        
        	for(int v = 0; v < valores.length(); v++) {
        		
        		if(typeNode.equals(SWPEND)) {			    			        	
	        		precio = valores.getJSONObject(v).getString("pz");
	        		System.out.println(precio);
	        		precio = valores.getJSONObject(v).getString("pz_ene");
	        		System.out.println(precio);
	        		precio = valores.getJSONObject(v).getString("pz_per");
	        		System.out.println(precio);
	        		precio = valores.getJSONObject(v).getString("pz_cng");
	        		System.out.println(precio);
		    	} else if(typeNode.equals(SWPML)) {		    		       		
	        		precio = valores.getJSONObject(v).getString("pml");
	        		System.out.println(precio);
	        		precio = valores.getJSONObject(v).getString("pml_ene");
	        		System.out.println(precio);
	        		precio = valores.getJSONObject(v).getString("pml_per");
	        		System.out.println(precio);
	        		precio = valores.getJSONObject(v).getString("pml_cng");
	        		System.out.println(precio);
		    	}								    	        		
	        		
        	}
        	
		}
			
		
		return jsonObject;
			
	}
	
}
