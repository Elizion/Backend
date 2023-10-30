package com.core.backend.model;

public enum MessageEnum {

	GET_DATE_OK("Fecha actual."),
	GET_DATE_ERROR("Error al conseguir la fecha actual."),	
	CREATE_USER_OK("Usuario creado correctamente."),
	CREATE_USER_ERROR("Error al crear el usuario."),
	ERROR_GET_ID_USER("Error al obtener el ID del usuario."),
	DUPLICATE_USER("El nombre de usuario ya existe, intente con otro."),
	INVALID_PASSWORD("El password debera tener al menos una mayuscula, un digito y al menos 6 digitos de longitud.");

	private String message;
	
	MessageEnum(String message) {
		this.message = message;		
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
}
