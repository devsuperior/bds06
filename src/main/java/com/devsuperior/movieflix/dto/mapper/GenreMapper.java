package com.devsuperior.movieflix.dto.mapper;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.entities.Genre;

public class GenreMapper {

  public static GenreDTO toDTO(Genre genre) {
    return GenreDTO.builder()
        .id(genre.getId())
        .name(genre.getName())
        .build();
  }

  public static Genre toEntity(GenreDTO genreDTO) {
    return Genre.builder()
        .id(genreDTO.getId())
        .name(genreDTO.getName())
        .build();
  }
}
