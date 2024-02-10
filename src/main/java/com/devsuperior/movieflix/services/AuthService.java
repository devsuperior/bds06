package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.dto.mapper.UserMapper;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ForbiddenException;
import com.devsuperior.movieflix.services.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AuthService authService;

  @Transactional(readOnly = true)
  public UserDTO getProfile(){
    return UserMapper.toDTO(authService.authenticated());
  }

  @Transactional(readOnly = true)
  public User authenticated() {
    try {
      String username = SecurityContextHolder.getContext()
          .getAuthentication()
          .getName();

      return userRepository.findByEmail(username);
    }
    catch (Exception e) {
      throw new UnauthorizedException("Invalid user");
    }
  }

  public void validate(Long userId) {
    User user = authenticated();

    if (!user.getId().equals(userId)) {
      throw new ForbiddenException("Access denied!");
    }

    if (!user.hasHole("ROLE_MEMBER") && !user.hasHole("ROLE_VISITOR")) {
      throw new ForbiddenException("Access denied!");
    }
  }
}