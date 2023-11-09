package com.core.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.core.backend.service.EmailService;
import com.core.backend.service.UserService;
import com.core.backend.util.ResponseHandler;

import com.core.backend.model.MessageEnum;

@RestController
@RequestMapping("/mail")
public class EmailController {

	@Autowired public UserService userService;
	@Autowired public EmailService emailService;
	
	
	
	@PostMapping("/send/xlsx")	
	public @ResponseBody ResponseEntity<?> sendEmailXlsx(
			@RequestParam("asunto") String asunto,
    		@RequestParam("mensaje") String mensaje,
    		@RequestParam("file") MultipartFile file) {
		
		boolean send = false;

		try {
					
			send = this.emailService.sendEmailXlsx(asunto, mensaje, file);
								
		} catch (Exception e) {
			return ResponseHandler.generateResponseSuccess(MessageEnum.EMAIL_SEND_ERROR.getMessage(), HttpStatus.CONFLICT, e.getMessage());
		}
		
		return ResponseHandler.generateResponseSuccess(MessageEnum.EMAIL_SEND_OK.getMessage(), HttpStatus.OK, send);
		
	}

	
}
