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
		UserMapper mapper = super.getSqlSession().getMapper( UserMapper.class );
		return mapper.getDateNow();    
	}

	@Override
	public Integer createdUser(UserModel userModel) throws PersistenceException {
		UserMapper mapper = super.getSqlSession().getMapper( UserMapper.class );		
		mapper.createdUser(userModel);
		return userModel.getIdUser();
	}

	@Override
	public String getUsername(String username) throws PersistenceException {
		UserMapper mapper = super.getSqlSession().getMapper( UserMapper.class );
		return mapper.getUsername(username);
	}

	@Override
	public void createdUserPicture(Integer idUser, String b64) throws PersistenceException {
		UserMapper mapper = super.getSqlSession().getMapper( UserMapper.class );
		mapper.createdUserImage(idUser, b64);		
	}

	@Override
	public void createdUserRoles(UserModel userModel) throws PersistenceException {
		UserMapper mapper = super.getSqlSession().getMapper( UserMapper.class );
		mapper.createdUserRoles(userModel);
	}

	@Override
	public AuthModel getUserAuth(String username) throws PersistenceException {
		UserMapper mapper = super.getSqlSession().getMapper( UserMapper.class );
		return mapper.getUserAuth(username);		
	}

}
