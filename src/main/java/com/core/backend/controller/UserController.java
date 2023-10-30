package com.core.backend.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.core.backend.service.UserService;
import com.core.backend.util.ResponseHandler;

import com.core.backend.model.MessageEnum;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	public UserService userService;

	@RequestMapping("/")
	public @ResponseBody String greeting() {
		return "Hello, World";
	}
	
	@GetMapping("/date/now")	
	public @ResponseBody ResponseEntity<?> getDateNow() {
		Date dateNow = null;
		try {
			dateNow = this.userService.getDateNow();
		} catch (RuntimeException e) {
			return ResponseHandler.generateResponseSuccess(MessageEnum.GET_DATE_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
		}
		return ResponseHandler.generateResponseSuccess(MessageEnum.GET_DATE_OK.getMessage(), HttpStatus.OK, dateNow);
	}
	
}
