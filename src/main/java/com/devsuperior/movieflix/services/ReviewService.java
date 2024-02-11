package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.mapper.MovieMapper;
import com.devsuperior.movieflix.dto.mapper.ReviewMapper;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

  @Autowired
  private ReviewRepository reviewRepository;

  @Autowired
  private AuthService authService;

  @Autowired
  private MovieService movieService;

  @Transactional
  @PreAuthorize("hasAnyRole('MEMBER')")
  public ReviewDTO save(final ReviewDTO reviewDTO) {
    final var movie = movieService.findById(reviewDTO.getMovieId());
    final var user = authService.authenticated();

    final var review = Review.builder()
        .text(reviewDTO.getText())
        .user(user)
        .movie(MovieMapper.toEntity(movie))
        .build();

    return ReviewMapper.toDTO(reviewRepository.save(review));
  }
}
