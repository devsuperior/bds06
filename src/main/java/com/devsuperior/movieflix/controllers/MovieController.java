package com.devsuperior.movieflix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.services.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	private MovieService service;

	@GetMapping(value = "/{id}") // Requisição GET com parâmetro
	public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {		
		MovieDTO dto = service.findById(id); // O dto correspondente ao id é retornado ou uma
		return ResponseEntity.ok().body(dto);
	}

}
