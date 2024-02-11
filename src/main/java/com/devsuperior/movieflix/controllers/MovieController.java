package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

  @Autowired
  private MovieService movieService;

  @GetMapping("/{id}")
  public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
    return ResponseEntity.ok()
        .body(movieService.findById(id));
  }

  @GetMapping
  public ResponseEntity<Page<MovieDTO>> findAllByGenre(
      @RequestParam(value = "genreId", defaultValue = "0") Long genreId,
      Pageable pageable
  ) {
    return ResponseEntity.ok()
        .body(movieService.findAllByGenre(genreId, pageable));
  }
}
