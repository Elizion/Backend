package com.core.backend.model;

import java.util.Set;

public class UserModel {
		
	public Long idUser;
	public String username;
	public String email;
	public String password;
	public Boolean enabled;
	public String picture;
	public Set<RoleModel> roles;
	
	public UserModel(String username, String email, Boolean enabled) {
		this.username 	= username;
        this.email 		= email;
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
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Set<RoleModel> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}

}