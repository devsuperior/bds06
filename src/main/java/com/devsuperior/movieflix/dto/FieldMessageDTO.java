package com.devsuperior.movieflix.dto;

public class FieldMessageDTO {

	private String fieldName;
	private String message;
	
	public FieldMessageDTO() {
	}

	public FieldMessageDTO(String fieldName, String message) {
		this.fieldName = fieldName;
		this.message = message;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
