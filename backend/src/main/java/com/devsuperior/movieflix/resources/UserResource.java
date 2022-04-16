package com.devsuperior.movieflix.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService userService; 
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findByEmail(@PathVariable Long id){
		UserDTO dto = userService.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/profile")
	public ResponseEntity<UserDTO> profile(){
		UserDTO dto = userService.profile();
		return ResponseEntity.ok(dto);
	}
}
