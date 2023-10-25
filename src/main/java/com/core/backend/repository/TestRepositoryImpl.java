package com.core.backend.repository;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.core.backend.mapper.TestMapper;

@Repository
public class TestRepositoryImpl extends GenericRepository implements TestRepository  {

	@Override
	public Date getDateNow() {	
		TestMapper mapper = super.getSqlSession().getMapper( TestMapper.class );
		return mapper.getDateNow();
	}
	
	
}
