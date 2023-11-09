package com.core.backend.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.exceptions.PersistenceException;

@Mapper
public interface EmailMapper {
	
	public Date getDateNow() throws PersistenceException;
	
}
