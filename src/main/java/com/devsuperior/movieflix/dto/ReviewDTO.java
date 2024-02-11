package com.devsuperior.movieflix.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReviewDTO {

  private Long id;

  @NotBlank(message = "not blank")
  private String text;

  private UserDTO user;

  private Long movieId;
}
