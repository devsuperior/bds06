package com.devsuperior.movieflix.services.exceptions;

public class UnauthorizedException extends RuntimeException {
  public UnauthorizedException(String msg) {
    super(msg);
  }
}