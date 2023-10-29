package com.core.backend.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.core.backend.model.UserModel;

@Mapper
public interface UserMapper {
	
	public Date getDateNow();
	
	public void createdUser(@Param("userModel") UserModel userModel);

	public UserModel getUserByUsername(@Param("username") String username);
	
}
