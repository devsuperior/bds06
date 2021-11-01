package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.MyEntityNotFoundException;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;
	
	@Autowired
	private ReviewRepository reviewRepository;

	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		Optional<Movie> obj = repository.findById(id);
		Movie entity = obj
				.orElseThrow(() -> new MyEntityNotFoundException("Entidade não encontrada!"));
		return new MovieDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<ReviewDTO> findReviewsByMovie(Long movieId) {
		List<Review> list = reviewRepository.findReviewsByMovie(movieId);
		return list.stream().map(entity -> new ReviewDTO(entity)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public Page<MovieDTO> findByGenre(Pageable pageable, Long genreId) {
		Page<Movie> page = repository.findByGenre(genreId, pageable);
		return page.map(entity -> new MovieDTO(entity));
	}

}