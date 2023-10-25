package com.core.backend.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
	
	public Date getDateNow();
	
}
