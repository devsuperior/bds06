package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.dto.mapper.GenreMapper;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

  @Autowired
  private GenreRepository genreRepository;

  public List<GenreDTO> findAll() {
    List<Genre> genreList = genreRepository.findAll();
    return genreList.stream()
        .map(GenreMapper::toDTO)
        .collect(Collectors.toList());
  }
}
