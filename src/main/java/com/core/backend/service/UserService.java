package com.core.backend.service;

import java.util.Date;
import java.util.Set;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.web.multipart.MultipartFile;

import com.core.backend.model.UserModel;

public interface UserService {

	public Date getDateNow() throws PersistenceException;
	
	public Long createdUser(UserModel userModel) throws PersistenceException;

	public String findByUsername(String username) throws PersistenceException;

	public void createdUserPicture(MultipartFile picture, Long idUser, UserModel userModel) throws Exception;

	public void createdUserRoles(Set<String> rolesRequest, Long idUser, UserModel userModel) throws PersistenceException;

	public void createdPasswordEncode(String password, UserModel userModel);

	public UserModel findByUsernameAuth(String username) throws PersistenceException;

}
