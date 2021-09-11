package com.devsuperior.movieflix.services.exceptions;

public class MyEntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MyEntityNotFoundException(String message) {
		super(message);
	}
	
}