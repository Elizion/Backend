package com.core.backend.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

	/*
	# Minúsculas:
	# \u00e1 -> á
	# \u00e9 -> é
	# \u00ed -> í
	# \u00f3 -> ó
	# \u00fa -> ú
	# Mayúsculas:
	# \u00c1 -> Á
	# \u00c9 -> É
	# \u00cd -> Í
	# \u00d3 -> Ó
	# \u00da -> Ú
	# Letra ñ o Ñ:
	# \u00f1 -> ñ
	# \u00d1 -> Ñ
	*/	
	
    public static ResponseEntity<Object> generateResponseSuccess(String message, HttpStatus status, Object response) {
    	Map<String, Object> map = new HashMap<String, Object>();
            				map.put("message", message);
            				map.put("status", status.value());
            				map.put("data", response);
        return new ResponseEntity<Object>(map,status);
    }
    
    public static ResponseEntity<Object> generateResponseError(String message, HttpStatus status, Object response) {
    	Map<String, Object> map = new HashMap<String, Object>();
    						map.put("message", message);
    						map.put("status", status.value());
    						map.put("data", response);
    	return new ResponseEntity<Object>(map,status);
    }
    
}
