package com.devsuperior.movieflix.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	public MovieDTO findMovieId(Long id) {
		Optional<Movie> movie = movieRepository.findById(id);
		return new MovieDTO(movie.orElseThrow(() -> new ResourceNotFoundException("Entity not found")));
	}

	public Page<MovieDTO> findAll(Pageable pageable, Long genreId) {
		Pageable sortedByTitle = 
					  PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("title"));
		Page<Movie> movies = movieRepository.findAllOrGenreId(pageable.getSort().isSorted()?pageable:sortedByTitle, genreId);
		return movies.map(x -> new MovieDTO(x));
	}
}
