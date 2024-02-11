package com.devsuperior.movieflix.dto.mapper;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
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

  public static Review toEntity(final ReviewDTO reviewDTO) {

    return Review.builder()
        .id(reviewDTO.getId())
        .text(reviewDTO.getText())
        .user(UserMapper.toEntity(reviewDTO.getUser()))
        .movie(Movie.builder()
            .id(reviewDTO.getMovieId())
            .build())
        .build();
  }
}
