package com.devsuperior.movieflix.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.services.AuthService;
import com.devsuperior.movieflix.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@Autowired
	private AuthService authService;
	
	@GetMapping(value = "/profile")
	public ResponseEntity<UserDTO> profile() {
		User user = authService.authenticated();
		
		UserDTO dto = service.findById( user.getId() );
		return ResponseEntity.ok().body(dto);
	}
}
