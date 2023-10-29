package com.core.backend.model;

import com.core.backend.service.FigureService;

public class Circle extends FigureService {

	@Override	
	public float calculateArea(float radio) {
		return 3.1416f * (radio * radio);
	}

}
