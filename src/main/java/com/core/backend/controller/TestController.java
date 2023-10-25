package com.core.backend.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.core.backend.model.ParentModel;
import com.core.backend.service.TestService;
import com.core.backend.util.ResponseHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/test")
public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	public TestService testService;

	@GetMapping("/dateNow")
	@ResponseBody
	public ResponseEntity<?> getDateNow() {
		Date dateNow = null;
		try {
			logger.info("Performing an action...");
			dateNow = this.testService.getDateNow();
			logger.debug("Action completed successfully.");
		} catch (Exception e) {
			return ResponseHandler.generateResponseModel("Error", HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
		}
		return ResponseHandler.generateResponseModel("Fecha actual.", HttpStatus.OK, dateNow);
	}
	
	@PostMapping("/read/xlsx")
	@ResponseBody
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
		String nameSheets = null;
		try {
			nameSheets = this.testService.getNameSheets(file);
		} catch (Exception e) {			                                            
			return ResponseHandler.generateResponseModel(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		return ResponseHandler.generateResponseModel("El archivo fue leido correctamente.", HttpStatus.OK, nameSheets);
	}
	
	@PostMapping("/read/big/xlsx")
	@ResponseBody
	public ResponseEntity<?> uploadBigFile(@RequestParam("file") MultipartFile file) {		
		try {
			this.testService.uploadBigFile(file);
		} catch (Exception e) {			                                            
			return ResponseHandler.generateResponseModel(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		return ResponseHandler.generateResponseModel("El archivo fue leido correctamente.", HttpStatus.OK, file.getSize());
	}

	@PostMapping("/read/jxls/xlsx")
	@ResponseBody
	public ResponseEntity<?> uploadFileJxls(@RequestParam("file") MultipartFile file) {		
		List<ParentModel> loadedData = null;		
		try {			
			loadedData = this.testService.uploadFileJxls(file);			
			for(ParentModel data: loadedData) {
				System.out.println(data.getSheetName() + " " + data.getHeaderName() + " " + data.getListChildModel().size());
			}			
		} catch (Exception e) {			                                            
			return ResponseHandler.generateResponseModel(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}		
		return ResponseHandler.generateResponseModel("El archivo fue leido correctamente.", HttpStatus.OK, loadedData);		
	}

}
