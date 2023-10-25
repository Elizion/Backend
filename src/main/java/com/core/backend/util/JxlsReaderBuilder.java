package com.core.backend.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReader;
import org.xml.sax.SAXException;


import com.core.backend.model.ChildModel;
import com.core.backend.model.ParentModel;

public class JxlsReaderBuilder {


	public static XLSReader build(List<String> sheetNames, Map<Object, Object> beans) throws IOException, SAXException {
		
    	String baseVarName="input_";
    	StringBuilder mapping = new StringBuilder("");
    	
    	mapping.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    	mapping.append("<workbook>");
    	int index=0;
    	
    	for (String sheetName : sheetNames) {
    		
    		ParentModel dataModel = new ParentModel();    		
			dataModel.setListChildModel(new ArrayList<ChildModel>());
			beans.put(baseVarName+index, dataModel);
			dataModel.setSheetName(sheetName);
			
    		mapping.append("<worksheet name=\"" + sheetName + "\">");
    		
    		mapping.append(	"<section startRow=\"0\" endRow=\"1\">");    		
    			mapping.append("<mapping cell=\"A1\">" + baseVarName + index + ".headerName</mapping>");					
    		mapping.append(	"</section>");
    		
    		mapping.append(	"<loop startRow=\"2\" endRow=\"2\" items=\"" + baseVarName + index + ".listChildModel\" var=\"listChildModel_" + index + "\" varType=\"com.core.backend.model.ChildModel\" >");
    		mapping.append(		"<section startRow=\"2\" endRow=\"2\">");    	
    		mapping.append(			"<mapping row=\"2\" col=\"0\">listChildModel_" + index + ".date</mapping>");
    		mapping.append(			"<mapping row=\"2\" col=\"1\">listChildModel_" + index + ".value1</mapping>");
    		mapping.append(			"<mapping row=\"2\" col=\"2\">listChildModel_" + index + ".value2</mapping>");
    		mapping.append(			"<mapping row=\"2\" col=\"3\">listChildModel_" + index + ".value3</mapping>");
    		mapping.append(			"<mapping row=\"2\" col=\"4\">listChildModel_" + index + ".value4</mapping>");
    		mapping.append(			"<mapping row=\"2\" col=\"5\">listChildModel_" + index + ".value5</mapping>");
    		mapping.append(			"<mapping row=\"2\" col=\"6\">listChildModel_" + index + ".value6</mapping>");
    		mapping.append(			"<mapping row=\"2\" col=\"7\">listChildModel_" + index + ".value7</mapping>");
    		mapping.append(			"<mapping row=\"2\" col=\"8\">listChildModel_" + index + ".value8</mapping>");
    		mapping.append(			"<mapping row=\"2\" col=\"9\">listChildModel_" + index + ".value9</mapping>");
    		mapping.append(			"<mapping row=\"2\" col=\"10\">listChildModel_" + index + ".value10</mapping>");
    		mapping.append(			"<mapping row=\"2\" col=\"11\">listChildModel_" + index + ".value11</mapping>");
    		mapping.append(			"<mapping row=\"2\" col=\"12\">listChildModel_" + index + ".value12</mapping>");
    		mapping.append(			"<mapping row=\"2\" col=\"13\">listChildModel_" + index + ".value13</mapping>");
    		mapping.append(			"<mapping row=\"2\" col=\"14\">listChildModel_" + index + ".value14</mapping>");
    		mapping.append(			"<mapping row=\"2\" col=\"15\">listChildModel_" + index + ".value15</mapping>");
    		mapping.append(			"<mapping row=\"2\" col=\"16\">listChildModel_" + index + ".value16</mapping>");
    		mapping.append(			"<mapping row=\"2\" col=\"17\">listChildModel_" + index + ".value17</mapping>");
    		mapping.append(			"<mapping row=\"2\" col=\"18\">listChildModel_" + index + ".value18</mapping>");
    		mapping.append(			"<mapping row=\"2\" col=\"19\">listChildModel_" + index + ".value19</mapping>");
    		mapping.append(			"<mapping row=\"2\" col=\"20\">listChildModel_" + index + ".value20</mapping>");
    		mapping.append(			"<mapping row=\"2\" col=\"21\">listChildModel_" + index + ".value21</mapping>");
    		mapping.append(			"<mapping row=\"2\" col=\"22\">listChildModel_" + index + ".value22</mapping>");
    		mapping.append(			"<mapping row=\"2\" col=\"23\">listChildModel_" + index + ".value23</mapping>");
    		mapping.append(			"<mapping row=\"2\" col=\"24\">listChildModel_" + index + ".value24</mapping>");
    		mapping.append(			"<mapping row=\"2\" col=\"25\">listChildModel_" + index + ".value25</mapping>");
    		mapping.append(		"</section>");
    		mapping.append(		"<loopbreakcondition>");
    		mapping.append(			"<rowcheck offset=\"0\">");
    		mapping.append(				"<cellcheck offset=\"1\"/>");
    		mapping.append(			"</rowcheck>");
    		mapping.append(		"</loopbreakcondition>");
    		mapping.append(	"</loop>");
    		mapping.append("</worksheet>");
    		index++;
		}
    	
    	mapping.append("</workbook>");
    	 
    	XLSReader reader = ReaderBuilder.buildFromXML(new ByteArrayInputStream(mapping.toString().getBytes()));
        	 
    	return reader;
    	
	}
    
}

