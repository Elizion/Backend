package com.core.backend.mapper;

import org.apache.ibatis.annotations.Param;

import com.core.backend.model.RoleModel;

public interface RoleMapper {

	public RoleModel findByName(@Param("role") String role);

}
