package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {

	@Autowired
	private MovieRepository movieRepository; 
	
	@Autowired
	private ReviewRepository reviewRepository ;
	
	@Autowired
	private AuthService authService;
	
	public ReviewDTO insert(ReviewDTO dto) {
		User user = authService.authenticated();
		authService.validateSelfOrAdmin(user.getId());
		Optional<Movie> movie = movieRepository.findById(dto.getMovieId());
		Review review = new Review();
		if(movie.isPresent()) {
			review = new Review(null, dto.getText(), user, movie.get());
			review = reviewRepository.save(review);
		} else {
			throw new ResourceNotFoundException("Movie not found");
		}
		return new ReviewDTO(review);
	}
	
	public List<ReviewDTO> findReviewsMovieId(Long id) {
		List<Review> reviews = reviewRepository.findByMovieId(id);
		return reviews.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
	}
	
}
