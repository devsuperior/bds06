package com.devsuperior.movieflix.controllers.exceptions;

import com.devsuperior.movieflix.services.exceptions.ForbiddenException;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import com.devsuperior.movieflix.services.exceptions.UnauthorizedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<StandardError> resourceNotFoundException(
      ResourceNotFoundException e, HttpServletRequest request
  ) {
    StandardError error = new StandardError();
    HttpStatus status = HttpStatus.NOT_FOUND;

    error.setTimestamp(Instant.now());
    error.setStatus(status.value());
    error.setError("Resource not found.");
    error.setMessage(e.getMessage());
    error.setPath(request.getRequestURI());

    return ResponseEntity.status(status).body(error);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<StandardError> dataIntegrityViolationException(
      DataIntegrityViolationException e, HttpServletRequest request
  ) {
    StandardError error = new StandardError();
    HttpStatus status = HttpStatus.BAD_REQUEST;

    error.setTimestamp(Instant.now());
    error.setStatus(status.value());
    error.setError("Data Integrity Violation");
    error.setMessage(e.getMessage());
    error.setPath(request.getRequestURI());

    return ResponseEntity.status(status).body(error);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<StandardError> methodArgumentNotValidException(
      MethodArgumentNotValidException e, HttpServletRequest request
  ) {
    StandardError error = new StandardError();
    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

    error.setTimestamp(Instant.now());
    error.setStatus(status.value());
    error.setError("Method Argument Not Valid");
    error.setMessage(e.getMessage());
    error.setPath(request.getRequestURI());

    return ResponseEntity.status(status).body(error);
  }

  @ExceptionHandler(ForbiddenException.class)
  public ResponseEntity<OAuthCustomError> forbiddenException(
      ForbiddenException e, HttpServletRequest request
  ) {
    HttpStatus status = HttpStatus.FORBIDDEN;
    OAuthCustomError error = new OAuthCustomError();

    error.setError("Forbidden");
    error.setErrorDescription(e.getMessage());

    return ResponseEntity.status(status).body(error);
  }

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<OAuthCustomError> unauthorizedException(
      UnauthorizedException e, HttpServletRequest request
  ) {
    HttpStatus status = HttpStatus.UNAUTHORIZED;
    OAuthCustomError error = new OAuthCustomError();

    error.setError("Unauthorized");
    error.setErrorDescription(e.getMessage());

    return ResponseEntity.status(status).body(error);
  }
}