package com.core.backend.repository;

import org.springframework.stereotype.Repository;

import com.core.backend.mapper.RoleMapper;
import com.core.backend.model.RoleModel;

@Repository
public class RoleRepositoryImpl extends GenericRepository implements RoleRepository  {

	@Override
	public RoleModel findByName(String role) {
		RoleMapper mapper = super.getSqlSession().getMapper( RoleMapper.class );		
		return mapper.findByName(role);
	}
	
}
