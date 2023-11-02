package com.core.backend.repository;

import java.util.Date;

import org.apache.ibatis.exceptions.PersistenceException;

import com.core.backend.model.UserModel;

public interface UserRepository {

	public Date getDateNow() throws PersistenceException;

	public Long createdUser(UserModel userModel) throws PersistenceException;

	public String findByUsername(String username) throws PersistenceException;

	public void createdUserPicture(Long idUser, String b64) throws PersistenceException;

	public void createdUserRoles(UserModel userModel) throws PersistenceException;

	public UserModel findByUsernameAuth(String username) throws PersistenceException;

}
