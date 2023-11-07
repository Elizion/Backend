package com.core.backend.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.exceptions.PersistenceException;

import com.core.backend.model.AuthModel;
import com.core.backend.model.UserModel;

@Mapper
public interface UserMapper {
	
	public Date getDateNow();

	public String getUsername(@Param("username") String username) throws PersistenceException;
	
	public void saveUser(@Param("userModel") UserModel userModel) throws PersistenceException;

	public void saveUserPicture(@Param("userModel") UserModel userModel) throws PersistenceException;

	public void saveUserRoles(@Param("userModel") UserModel userModel) throws PersistenceException;
	
	public void saveUserAddress(@Param("userModel") UserModel userModel) throws PersistenceException;

	public AuthModel getUserAuth(@Param("username") String username) throws PersistenceException;	
	
}
