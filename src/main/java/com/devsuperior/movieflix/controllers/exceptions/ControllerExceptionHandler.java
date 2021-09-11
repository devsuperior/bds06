package com.devsuperior.movieflix.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.movieflix.services.exceptions.MyEntityNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(MyEntityNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(MyEntityNotFoundException e,
			HttpServletRequest request) {
		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Recurso não encontrado!");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e,
			HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError error = new ValidationError();
		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError("Erro de validação!");
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		/*
		 * Laço que percorre uma lista de erros nativa da exceção. Cada objeto dessa
		 * lista é armazenado como um objeto FieldMessage na lista errors. Essa lista é
		 * a que compõe junto com os demais atributos de um StandardError um
		 * ValidationError.
		 */
		for (FieldError obj : e.getBindingResult().getFieldErrors()) {
			error.addError(obj.getField(), obj.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(error);
	}

}