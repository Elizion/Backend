package com.core.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.core.backend.model.RoleModel;
import com.core.backend.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public RoleModel findByName(String role) {
		RoleModel roleModel = this.roleRepository.findByName(role);	
		return roleModel;
	}
	
}
