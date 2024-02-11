package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.mapper.MovieMapper;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

  @Autowired
  private MovieRepository movieRepository;

  @Autowired
  private GenreService genreService;

  @Transactional(readOnly = true)
  @PreAuthorize("hasAnyRole('MEMBER','VISITOR')")
  public MovieDTO findById(final Long id) {
    final var movie = movieRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

    return MovieMapper.toDTO(movie);
  }

  @Transactional(readOnly = true)
  @PreAuthorize("hasAnyRole('MEMBER','VISITOR')")
  public Page<MovieDTO> findAllByGenre(final Long genreId, final Pageable pageable) {
    final var genre = (genreId == 0) ? null : List.of(genreService.getGenreById(genreId));
    final var page = movieRepository.findMovieByGenre(genre, pageable);
    return page.map(MovieMapper::toDTO);
  }
}
