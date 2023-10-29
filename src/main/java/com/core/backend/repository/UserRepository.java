package com.core.backend.repository;

import java.util.Date;

import org.apache.ibatis.exceptions.PersistenceException;

import com.core.backend.model.UserModel;

public interface UserRepository {

	public Date getDateNow() throws PersistenceException;

	public void createdUser(UserModel userModel) throws PersistenceException;

	public UserModel getUserByUsername(String username) throws PersistenceException;

}
