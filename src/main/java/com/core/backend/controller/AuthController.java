package com.core.backend.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.core.backend.model.UserModel;
import com.core.backend.payload.LoginRequest;
import com.core.backend.util.ResponseHandler;
import com.core.backend.util.ServiceUtils;

import com.core.backend.model.MessageEnum;
import com.core.backend.service.RoleService;
import com.core.backend.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired UserService userService;
	@Autowired RoleService roleService;

	@PostMapping("/signup")
    public ResponseEntity<?> createdUser(
    		@RequestParam("picture") MultipartFile picture,
    		@RequestParam("username") String username,
    		@RequestParam("email") String email,
    		@RequestParam("roles") Set<String> rolesRequest,
    		@RequestParam("enabled") Boolean enabled,
    		@RequestParam("password") String password) throws Exception {
		
		String usernameFind = this.userService.findByUsername(username);
		
		if (username.equals(usernameFind)) {
			return ResponseHandler.generateResponseError(MessageEnum.DUPLICATE_USER.getMessage(), HttpStatus.BAD_REQUEST, username);
		}
		
		boolean isValidPassword = ServiceUtils.matchesPolicy(password);
		
		if (!isValidPassword) {
			return ResponseHandler.generateResponseError(MessageEnum.INVALID_PASSWORD.getMessage(), HttpStatus.BAD_REQUEST, isValidPassword);
		}
		
		UserModel userModel = new UserModel(username, username+email, enabled);
		this.userService.createdPasswordEncode(password, userModel);		
		Long idUser = this.userService.createdUser(userModel);			
		this.userService.createdUserPicture(picture, idUser, userModel);							
		this.userService.createdUserRoles(rolesRequest, idUser, userModel);

		return ResponseHandler.generateResponseSuccess(MessageEnum.CREATE_USER_OK.getMessage(), HttpStatus.CREATED, userModel);
		
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();
		
		System.out.println(username);
		System.out.println(password);
		
		UserModel userModel = this.userService.findByUsernameAuth(username);

		return ResponseHandler.generateResponseSuccess(MessageEnum.GET_USER_OK.getMessage(), HttpStatus.OK, userModel);
		
	}
	
	

}
