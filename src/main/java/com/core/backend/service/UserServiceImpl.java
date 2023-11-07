package com.core.backend.service;

import java.io.File;
import java.io.InputStream;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
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
	public Date getDateNow() throws Exception {
		return this.userRepository.getDateNow();
	}

	@Override
	public String getUsername(String username) throws Exception {
		return this.userRepository.getUsername(username);
	}

	@Override
	public String getPasswordEncode(String password) throws Exception {
		return this.encoder.encode(password);		
	}
	
	@Override
	public String getPictureToBase64(MultipartFile picture) throws Exception {
		InputStream inputStream = picture.getInputStream();
	    byte[] output = ServiceUtils.readFileFromStream(inputStream);
		return Base64.getEncoder().encodeToString(output);
	}
		
	@Override
	public String getPictureDefaultToBase64(String imageUserDefault) throws Exception {
		byte[] fileContent = FileUtils.readFileToByteArray(new File(imageUserDefault));	
		return Base64.getEncoder().encodeToString(fileContent);		
	}
	
	@Override
	public Set<RoleModel> getUserRoles(Set<String> rolesRequest) throws Exception {		
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
		return roles;
	}

	@Override
	public Integer saveUser(UserModel userModel) throws Exception {
		return this.userRepository.saveUser(userModel);
	}
	
	@Override
	public void saveUserPicture(UserModel userModel) throws Exception {
		this.userRepository.saveUserPicture(userModel);
	}
	
	@Override
	public void saveUserRoles(UserModel userModel) throws Exception {
		this.userRepository.saveUserRoles(userModel);
	}
	
	@Override
	public AuthModel getUserAuth(String username) throws Exception {
		AuthModel userModel = this.userRepository.getUserAuth(username);
		return userModel;
	}

	@Override
	public void saveUserAddress(UserModel userModel) throws Exception {
		this.userRepository.saveUserAddress(userModel);
	}
		
}
