package com.core.backend.service;

import java.util.Date;
import java.util.Set;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.web.multipart.MultipartFile;

import com.core.backend.model.AuthModel;
import com.core.backend.model.UserModel;

public interface UserService {

	public Date getDateNow() throws PersistenceException;
	
	public Integer createdUser(UserModel userModel) throws PersistenceException;

	public String getUsername(String username) throws PersistenceException;

	public void createdUserPicture(MultipartFile picture, UserModel userModel) throws Exception;

	public void createdUserRoles(Set<String> rolesRequest, UserModel userModel) throws PersistenceException;

	public void createdPasswordEncode(String password, UserModel userModel);

	public AuthModel getUserAuth(String username) throws PersistenceException;

}
