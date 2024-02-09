package com.devsuperior.movieflix.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ValidationError extends StandardError {
  private List<FieldMessage> errors = new ArrayList<>();
}