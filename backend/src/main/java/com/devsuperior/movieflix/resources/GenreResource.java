package com.devsuperior.movieflix.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.repositories.GenreRepository;

@RestController
@RequestMapping(value = "/genres")
public class GenreResource {

	@Autowired
	private GenreRepository genreRepository; 
	
	@GetMapping
	public ResponseEntity<List<GenreDTO>> findAll(){
		List<Genre> genres = genreRepository.findAll();
		return ResponseEntity.ok(genres.stream().map(x -> new GenreDTO(x)).collect(Collectors.toList()));
	}
}
