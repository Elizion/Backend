package com.core.backend.repository;

import com.core.backend.model.RoleModel;

public interface RoleRepository {

	public RoleModel findByName(String role);

}
