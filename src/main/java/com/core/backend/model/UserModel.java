package com.core.backend.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserModel {
	
	@JsonIgnore
	public Long idUser;
	public String username;
	public String email;
	public String password;
	public Boolean enabled;
	public Set<RoleModel> roles;
	
	public UserModel(String username, String email, String password, Boolean enabled) {
		this.username 	= username;
        this.email 		= email;
        this.password 	= password;
        this.enabled 	= enabled;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Set<RoleModel> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}

}