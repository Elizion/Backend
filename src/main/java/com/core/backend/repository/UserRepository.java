package com.core.backend.repository;

import java.util.Date;

import org.apache.ibatis.exceptions.PersistenceException;

import com.core.backend.model.AuthModel;
import com.core.backend.model.UserModel;

public interface UserRepository {

	public Date getDateNow() throws PersistenceException;
	
	public String getUsername(String username) throws PersistenceException;
	
	public Integer saveUser(UserModel userModel) throws PersistenceException;

	public void saveUserPicture(UserModel userModel) throws PersistenceException;

	public void saveUserRoles(UserModel userModel) throws PersistenceException;
	
	public void saveUserAddress(UserModel userModel) throws PersistenceException;
	
	public AuthModel getUserAuth(String username) throws PersistenceException;	

}
