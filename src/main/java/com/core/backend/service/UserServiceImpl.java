package com.core.backend.service;

import java.io.InputStream;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.core.backend.model.AuthModel;
import com.core.backend.model.RoleEnum;
import com.core.backend.model.RoleModel;
import com.core.backend.model.UserModel;
import com.core.backend.repository.RoleRepository;
import com.core.backend.repository.UserRepository;
import com.core.backend.util.ServiceUtils;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired UserRepository userRepository;	
	@Autowired RoleRepository roleRepository;	
	@Autowired PasswordEncoder encoder;
	
	@Override
	public Date getDateNow() throws PersistenceException {
		return this.userRepository.getDateNow();
	}

	@Override
	public Integer createdUser(UserModel userModel) throws PersistenceException {
		return this.userRepository.createdUser(userModel);
	}

	@Override
	public String getUsername(String username) throws PersistenceException {
		return this.userRepository.getUsername(username);
	}

	@Override
	public void createdPasswordEncode(String password, UserModel userModel) {
		String passwordEncode = this.encoder.encode(password);
		userModel.setPassword(passwordEncode);
	}
	
	@Override
	public void createdUserPicture(MultipartFile picture, UserModel userModel) throws Exception {
		InputStream inputStream = picture.getInputStream();
	    byte[] output = ServiceUtils.readFileFromStream(inputStream);
		String b64 = Base64.getEncoder().encodeToString(output);					
		this.userRepository.createdUserPicture(userModel.getIdUser(), b64);
		userModel.setPicture(b64);
	}

	@Override
	public void createdUserRoles(Set<String> rolesRequest, UserModel userModel) throws PersistenceException {		
		Set<RoleModel> roles = new HashSet<>();
		Set<String> strRoles = rolesRequest;				
		if (strRoles == null || strRoles.size() == 0) {			
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
		this.userRepository.createdUserRoles(userModel);
	}

	@Override
	public AuthModel getUserAuth(String username) throws PersistenceException {
		AuthModel userModel = this.userRepository.getUserAuth(username);
		return userModel;
	}
	
}
