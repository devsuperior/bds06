package com.devsuperior.movieflix.dto;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDTO extends StandardErrorDTO {

	private List<FieldMessageDTO> errors = new ArrayList<>();

	public List<FieldMessageDTO> getErrors() {
		return errors;
	}
	
    public void addError(String fieldName, String message) {
    	errors.removeIf(x -> x.getFieldName().equals(fieldName));
    	errors.add(new FieldMessageDTO(fieldName, message));
    }
}
