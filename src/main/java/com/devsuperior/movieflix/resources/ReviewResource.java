package com.devsuperior.movieflix.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.services.AuthService;
import com.devsuperior.movieflix.services.ReviewService;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewResource {

	@Autowired
	private AuthService authService;

	@Autowired
	private ReviewService reviewService;

	@PostMapping
	public ResponseEntity<ReviewDTO> save(@RequestBody ReviewDTO dto) {
		com.devsuperior.movieflix.entities.User user = authService.authenticated();

		UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getEmail());

		dto.setUser(userDTO);
		dto = reviewService.save(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getMovieId())
				.toUri();
		return ResponseEntity.created(uri).body(dto);
	}
}
