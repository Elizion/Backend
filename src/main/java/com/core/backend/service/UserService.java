package com.core.backend.service;

import java.util.Date;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.core.backend.model.AuthModel;
import com.core.backend.model.RoleModel;
import com.core.backend.model.UserModel;

public interface UserService {

	public Date getDateNow() throws Exception;
	
	public String getUsername(String username) throws Exception;

	public String getPasswordEncode(String password) throws Exception;
	
	public String getPictureToBase64(MultipartFile picture) throws Exception;
	
	public String getPictureDefaultToBase64(String imageUserDefault) throws Exception;
	
	public Set<RoleModel> getUserRoles(Set<String> rolesRequest) throws Exception;

	public Integer saveUser(UserModel userModel) throws Exception;	

	public void saveUserPicture(UserModel userModel) throws Exception;
	
	public void saveUserRoles(UserModel userModel) throws Exception;
	
	public void saveUserAddress(UserModel userModel) throws Exception;
	
	public AuthModel getUserAuth(String username) throws Exception;

}
