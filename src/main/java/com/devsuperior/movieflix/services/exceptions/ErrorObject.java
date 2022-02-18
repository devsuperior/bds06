package com.devsuperior.movieflix.services.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorObject {

	private final String message;
	private final String field;
	private final Object parameter;
}
