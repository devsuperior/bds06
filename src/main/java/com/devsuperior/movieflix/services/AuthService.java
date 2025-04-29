package com.devsuperior.movieflix.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.UserRepository;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	
	protected User authenticated() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
			String username = jwtPrincipal.getClaim("username");
			return userRepository.findByEmail(username);
		}
		catch (Exception e) {
			throw new UsernameNotFoundException("Invalid user");
		}
	}
}
