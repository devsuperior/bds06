package com.devsuperior.movieflix.controllers.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
// @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OAuthCustomError {
  private String error;

  @JsonProperty("error_description")
  private String errorDescription;
}