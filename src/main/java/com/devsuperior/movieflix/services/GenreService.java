package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.dto.mapper.GenreMapper;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

  @Autowired
  private GenreRepository genreRepository;

  @PreAuthorize("hasAnyRole('MEMBER','VISITOR')")
  public List<GenreDTO> findAll() {
    List<Genre> genreList = genreRepository.findAll();
    return genreList.stream()
        .map(GenreMapper::toDTO)
        .collect(Collectors.toList());
  }

  public Genre getGenreById(final Long id) {
    return genreRepository.getOne(id);
  }
}
