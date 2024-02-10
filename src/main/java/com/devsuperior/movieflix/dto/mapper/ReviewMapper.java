package com.devsuperior.movieflix.dto.mapper;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.Review;

public class ReviewMapper {

  public static ReviewDTO toDTO(final Review review) {
    return ReviewDTO.builder()
        .id(review.getId())
        .text(review.getText())
        .user(UserMapper.toDTO(review.getUser()))
        .movieId(review.getMovie().getId())
        .build();
  }
}
