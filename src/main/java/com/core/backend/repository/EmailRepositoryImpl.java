package com.core.backend.repository;

import java.util.Date;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.stereotype.Repository;

import com.core.backend.mapper.EmailMapper;

@Repository
public class EmailRepositoryImpl extends GenericRepository implements EmailRepository  {

	@Override
	public Date getDateNow() throws PersistenceException {			
		EmailMapper mapper = super.getSqlSession().getMapper(EmailMapper.class);
		return mapper.getDateNow();    
	}

}
