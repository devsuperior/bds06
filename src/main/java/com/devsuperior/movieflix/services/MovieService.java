package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.mapper.MovieMapper;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {

  @Autowired
  private MovieRepository movieRepository;

  @Autowired
  private GenreRepository genreRepository;

  @Transactional(readOnly = true)
  public MovieDTO findById(final Long id) {
    final var movie = movieRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

    return MovieMapper.toDTO(movie);
  }
}
