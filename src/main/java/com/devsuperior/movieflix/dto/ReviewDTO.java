package com.devsuperior.movieflix.dto;

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
  private String text;
  private UserDTO user;
  private Long movieId;
}
