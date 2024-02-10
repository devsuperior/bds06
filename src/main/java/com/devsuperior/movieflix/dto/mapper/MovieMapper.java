package com.devsuperior.movieflix.dto.mapper;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.entities.Movie;

import java.util.stream.Collectors;

public class MovieMapper {
  public static MovieDTO toDTO(Movie movie) {
    return MovieDTO.builder()
        .id(movie.getId())
        .title(movie.getTitle())
        .subTitle(movie.getSubTitle())
        .year(movie.getYear())
        .imgUrl(movie.getImgUrl())
        .synopsis(movie.getSynopsis())
        .genre(GenreMapper.toDTO(movie.getGenre()))
        .reviews(movie.getReviews()
            .stream()
            .map(ReviewMapper::toDTO)
            .collect(Collectors.toList()))
        .build();
  }
}
