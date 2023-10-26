package com.core.backend.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import com.core.backend.model.ParentModel;

public interface TestService {

	public Date getDateNow();

	public String getNameSheets(MultipartFile file) throws IOException, InvalidFormatException;

	public void uploadBigFile(MultipartFile file) throws IOException;

	public List<ParentModel> uploadFileJxls(MultipartFile file) throws Exception;

	public JSONObject getDataCenace(String system, String process, List<String> listNode, String dateStart, String dateEnd, String typeNode);

	public boolean getOpenBrowser() throws InterruptedException;

}
