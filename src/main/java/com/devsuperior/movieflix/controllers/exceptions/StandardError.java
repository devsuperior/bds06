package com.devsuperior.movieflix.controllers.exceptions;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StandardError {
  private Instant timestamp;
  private Integer status;
  private String error;
  private String message;
  private String path;
}