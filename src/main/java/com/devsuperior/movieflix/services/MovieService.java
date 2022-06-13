package com.devsuperior.movieflix.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.entities.projection.MovieProjection;
import com.devsuperior.movieflix.repositories.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Transactional(readOnly = true)
	public Optional<MovieProjection> findById(Long id) {
		Optional<MovieProjection> obj = movieRepository.findByIdd(id);
		//Movie movie = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found HERE"));
		return obj;
	}

}
