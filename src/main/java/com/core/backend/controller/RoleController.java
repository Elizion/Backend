package com.core.backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

	@PostMapping("/created")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public String createdRole() {
		return "Created role.";
	}
	
}
