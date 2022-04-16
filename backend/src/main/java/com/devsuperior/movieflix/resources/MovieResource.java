package com.devsuperior.movieflix.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.services.MovieService;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {

	@Autowired
	private MovieService movieService;
	
	@GetMapping
	public ResponseEntity<Page<MovieDTO>> findAll(
			Pageable pageable, 
			@RequestParam(name = "genreId", defaultValue = "0L") Long genreId){
		Page<MovieDTO> movies = movieService.findAll(pageable, genreId);
		return ResponseEntity.ok(movies);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok(movieService.findMovieId(id));
	}
}
