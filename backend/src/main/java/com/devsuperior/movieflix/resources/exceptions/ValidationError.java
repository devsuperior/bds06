package com.devsuperior.movieflix.resources.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError implements Serializable{
	private static final long serialVersionUID = 1L;

	List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError() {
	}

	public List<FieldMessage> geterrors() {
		return errors;
	}
	
	public void addError(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}

}
