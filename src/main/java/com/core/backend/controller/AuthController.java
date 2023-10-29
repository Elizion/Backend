package com.core.backend.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.core.backend.model.UserModel;
import com.core.backend.reponse.JwtResponse;
import com.core.backend.repository.RoleRepository;
import com.core.backend.repository.UserRepository;
import com.core.backend.request.LoginRequest;
import com.core.backend.request.SignupRequest;
import com.core.backend.util.ResponseHandler;

import jakarta.validation.Valid;

import com.core.backend.model.MessageEnum;
import com.core.backend.security.JwtUtils;
import com.core.backend.service.UserDetailsImpl;
import com.core.backend.model.RoleEnum;
import com.core.backend.model.RoleModel;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
		String password = this.encoder.encode(signUpRequest.getPassword());
		System.out.println(password);
		System.out.println(password);
		System.out.println(password);
		UserModel userModel = new UserModel(signUpRequest.getUsername(), signUpRequest.getEmail(), password,
				signUpRequest.getEnabled());
		Set<String> strRoles = signUpRequest.getRoles();
		Set<RoleModel> roles = new HashSet<>();
		if (strRoles == null) {
			RoleModel userRole = this.roleRepository.findByName(RoleEnum.ROLE_USER.toString());
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "ROLE_ADMIN":
					RoleModel adminRole = this.roleRepository.findByName(RoleEnum.ROLE_ADMIN.toString());
					roles.add(adminRole);
					break;
				case "ROLE_MODEATOR":
					RoleModel modRole = this.roleRepository.findByName(RoleEnum.ROLE_MODERATOR.toString());
					roles.add(modRole);
					break;
				case "ROLE_USER":
					RoleModel userRole = this.roleRepository.findByName(RoleEnum.ROLE_USER.toString());
					roles.add(userRole);
					break;
				default:
					RoleModel defaulRole = this.roleRepository.findByName(RoleEnum.ROLE_DEFAULT.toString());
					roles.add(defaulRole);
				}
			});
		}
		userModel.setRoles(roles);
		JSONObject jsonObject = new JSONObject(userModel);
		String myJson = jsonObject.toString();
		System.out.println(myJson);
		this.userRepository.createdUser(userModel);
		return ResponseHandler.generateResponseModel(MessageEnum.CREATE_USER_OK.getMessage(), HttpStatus.CREATED,
				userModel);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

}
