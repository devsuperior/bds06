package com.devsuperior.movieflix.components;

import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenEnhancer implements TokenEnhancer {

  @Autowired
  private UserRepository userRepository;

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
    User user = userRepository.findByEmail(oAuth2Authentication.getName());

    Map<String, Object> map = new HashMap<>();
    map.put("userName", user.getUsername());
    map.put("userId", user.getId());

    DefaultOAuth2AccessToken token =  (DefaultOAuth2AccessToken) oAuth2AccessToken;
    token.setAdditionalInformation(map);

    return oAuth2AccessToken;
  }
}