package com.core.backend.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.core.backend.model.UserModel;

@Mapper
public interface UserMapper {
	
	public Date getDateNow();
	
	public Long createdUser(@Param("userModel") UserModel userModel);

	public String findByUsername(@Param("username") String username);

	public void createdUserImage(@Param("idUser") Long idUser, @Param("b64") String b64);

	public void createdUserRoles(@Param("userModel") UserModel userModel);
	
}
