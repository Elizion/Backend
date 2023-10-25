package com.core.backend.model;

import com.core.backend.service.Figure;

public class Circle extends Figure {

	@Override	
	public float calculateArea(float radio) {
		return 3.1416f * (radio * radio);
	}

}
