package com.core.backend.repository;

import java.util.Date;

import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.stereotype.Repository;

import com.core.backend.mapper.UserMapper;
import com.core.backend.model.AuthModel;
import com.core.backend.model.UserModel;

@Repository
public class UserRepositoryImpl extends GenericRepository implements UserRepository  {

	@Override
	public Date getDateNow() throws PersistenceException {			
		UserMapper mapper = super.getSqlSession().getMapper(UserMapper.class);
		return mapper.getDateNow();    
	}

	@Override
	public String getUsername(String username) throws PersistenceException {
		UserMapper mapper = super.getSqlSession().getMapper(UserMapper.class);
		return mapper.getUsername(username);
	}

	@Override
	public Integer saveUser(UserModel userModel) throws PersistenceException {
		UserMapper mapper = super.getSqlSession().getMapper(UserMapper.class);		
		mapper.saveUser(userModel);
		return userModel.getIdUser();
	}
	
	@Override
	public void saveUserPicture(UserModel userModel) throws PersistenceException {
		UserMapper mapper = super.getSqlSession().getMapper(UserMapper.class);		
		mapper.saveUserPicture(userModel);		
	}

	@Override
	public void saveUserRoles(UserModel userModel) throws PersistenceException {
		UserMapper mapper = super.getSqlSession().getMapper(UserMapper.class);
		mapper.saveUserRoles(userModel);
	}

	@Override
	public void saveUserAddress(UserModel userModel) throws PersistenceException {
		UserMapper mapper = super.getSqlSession().getMapper(UserMapper.class);
		mapper.saveUserAddress(userModel);
	}
	
	@Override
	public AuthModel getUserAuth(String username) throws PersistenceException {
		UserMapper mapper = super.getSqlSession().getMapper(UserMapper.class);
		return mapper.getUserAuth(username);
	}

}
