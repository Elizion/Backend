package com.core.backend.repository;

import java.util.Date;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.stereotype.Repository;

import com.core.backend.mapper.UserMapper;
import com.core.backend.model.UserModel;

@Repository
public class UserRepositoryImpl extends GenericRepository implements UserRepository  {

	@Override
	public Date getDateNow() throws PersistenceException {			
		UserMapper mapper = super.getSqlSession().getMapper( UserMapper.class );
		return mapper.getDateNow();    
	}

	@Override
	public void createdUser(UserModel userModel) throws PersistenceException {
		UserMapper mapper = super.getSqlSession().getMapper( UserMapper.class );
		mapper.createdUser(userModel);
	}

	@Override
	public UserModel getUserByUsername(String username) throws PersistenceException {
		UserMapper mapper = super.getSqlSession().getMapper( UserMapper.class );
		return mapper.getUserByUsername(username);
	}

}
