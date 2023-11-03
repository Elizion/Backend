package com.core.backend.repository;

import java.util.Date;

import org.apache.ibatis.exceptions.PersistenceException;

import com.core.backend.model.AuthModel;
import com.core.backend.model.UserModel;

public interface UserRepository {

	public Date getDateNow() throws PersistenceException;

	public Integer createdUser(UserModel userModel) throws PersistenceException;

	public String getUsername(String username) throws PersistenceException;

	public void createdUserPicture(Integer idUser, String b64) throws PersistenceException;

	public void createdUserRoles(UserModel userModel) throws PersistenceException;

	public AuthModel getUserAuth(String username) throws PersistenceException;

}
