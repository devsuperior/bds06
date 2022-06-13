package com.devsuperior.movieflix.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.projection.MovieProjection;
import com.devsuperior.movieflix.repositories.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Transactional(readOnly = true)
	public Optional<MovieProjection> findById(Long id) {
		Optional<MovieProjection> obj = movieRepository.findByIdd(id);
		return obj;
	}

	@Transactional(readOnly = true)
	public Page<MovieDTO> findByGenre(Integer genreId, Pageable pageable) {
		
		Page<Movie> list = movieRepository.findByGenre(genreId, pageable);
		return list.map(x -> new MovieDTO(x));
	}

}
