package com.core.backend.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.core.backend.model.UserModel;
import com.core.backend.payload.JwtResponse;
import com.core.backend.payload.LoginRequest;
import com.core.backend.security.JwtUtils;
import com.core.backend.util.ResponseHandler;
import com.core.backend.util.ServiceUtils;

import com.core.backend.model.MessageEnum;
import com.core.backend.service.RoleService;
import com.core.backend.service.UserDetailsImpl;
import com.core.backend.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired AuthenticationManager authenticationManager;
	@Autowired UserService userService;
	@Autowired RoleService roleService;
	@Autowired JwtUtils jwtUtils;
	
	@PostMapping("/signup")
    public ResponseEntity<?> createdUser(
    		@RequestParam("picture") MultipartFile picture,
    		@RequestParam("username") String username,
    		@RequestParam("email") String email,
    		@RequestParam("roles") Set<String> rolesRequest,
    		@RequestParam("enabled") Boolean enabled,
    		@RequestParam("password") String password) throws Exception {		
		String usernameFind = this.userService.getUsername(username);		
		if (username.equals(usernameFind)) {
			return ResponseHandler.generateResponseError(MessageEnum.DUPLICATE_USER.getMessage(), HttpStatus.BAD_REQUEST, username);
		}		
		boolean isValidPassword = ServiceUtils.matchesPolicy(password);		
		if (!isValidPassword) {
			return ResponseHandler.generateResponseError(MessageEnum.INVALID_PASSWORD.getMessage(), HttpStatus.BAD_REQUEST, isValidPassword);
		}		
		UserModel userModel = new UserModel();		
		userModel.setEmail(username+email);
		userModel.setUsername(username);		
		this.userService.createdPasswordEncode(password, userModel);		
		Integer idUser = this.userService.createdUser(userModel);		
		userModel.setIdUser(idUser);
		userModel.setEnabled(true);
		this.userService.createdUserPicture(picture, userModel);							
		this.userService.createdUserRoles(rolesRequest, userModel);		
		return ResponseHandler.generateResponseSuccess(MessageEnum.CREATE_USER_OK.getMessage(), HttpStatus.CREATED, userModel);		
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> getUser(@RequestBody LoginRequest loginRequest) {		
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));	
	}

}
