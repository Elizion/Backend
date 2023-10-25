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
import org.jxls.reader.ReaderConfig;
import org.jxls.reader.XLSReadMessage;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.core.backend.model.ParentModel;
import com.core.backend.repository.TestRepository;
import com.core.backend.util.JxlsReaderBuilder;
import com.core.backend.util.ServiceUtils;
import com.core.backend.util.JxlsDateConverter;
import com.monitorjbl.xlsx.StreamingReader;


@Service
public class TestServiceImpl implements TestService {

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
}
