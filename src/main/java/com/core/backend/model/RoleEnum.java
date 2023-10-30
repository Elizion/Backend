package com.core.backend.model;

public enum RoleEnum {
	
	ROLE_ADMIN("ROLE_ADMIN"), 
	ROLE_USER("ROLE_USER"), 
	ROLE_MODERATOR("ROLE_MODERATOR"),	
	ROLE_DEFAULT("ROLE_DEFAULT");

	private String nameRole;

	RoleEnum(String nameRole) {
		this.nameRole = nameRole;
	}

	public String getNameRole() {
		return nameRole;
	}

	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}

	public static RoleEnum getNameRoleEnum(String nameRole) {
		for (RoleEnum roleEnum : RoleEnum.values()) {
			if (roleEnum.getNameRole().equals(nameRole)) {
				return roleEnum;
			}
		}
		return null;
	}
	
}