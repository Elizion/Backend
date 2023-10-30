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
	public Long createdUser(UserModel userModel) throws PersistenceException {
		UserMapper mapper = super.getSqlSession().getMapper( UserMapper.class );
		return mapper.createdUser(userModel);
	}

	@Override
	public String findByUsername(String username) throws PersistenceException {
		UserMapper mapper = super.getSqlSession().getMapper( UserMapper.class );
		return mapper.findByUsername(username);
	}

	@Override
	public void createdUserPicture(Long idUser, String b64) {
		UserMapper mapper = super.getSqlSession().getMapper( UserMapper.class );
		mapper.createdUserImage(idUser, b64);		
	}

	@Override
	public void createdUserRoles(UserModel userModel) {
		UserMapper mapper = super.getSqlSession().getMapper( UserMapper.class );
		mapper.createdUserRoles(userModel);
	}

}
