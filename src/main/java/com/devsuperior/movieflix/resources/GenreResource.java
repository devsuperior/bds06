package com.devsuperior.movieflix.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.services.GenreService;

@RestController
@RequestMapping(value = "/genres")
public class GenreResource {

	@Autowired
	private GenreService genreService;
	
	@GetMapping
	public ResponseEntity<List<GenreDTO>> AllGenres() {
		List<GenreDTO> list = genreService.AllGenres();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "hello")
	public ResponseEntity<String> HelloWord() {		
		return ResponseEntity.ok("Hello Word de novo");
	}

}
