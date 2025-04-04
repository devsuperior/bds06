package com.devsuperior.movieflix.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.tests.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ReviewResourceIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TokenUtil tokenUtil;

	@Autowired
	private ObjectMapper objectMapper;

	private String visitorUsername;
	private String visitorPassword;
	private String memberUsername;
	private String memberPassword;
	private String memberToken, visitorToken, invalidToken;
	
	@BeforeEach
	void setUp() throws Exception {
		
		visitorUsername = "bob@gmail.com";
		visitorPassword = "123456";
		memberUsername = "ana@gmail.com";
		memberPassword = "123456";
		
		visitorToken = tokenUtil.obtainAccessToken(mockMvc, visitorUsername, visitorPassword);
		memberToken = tokenUtil.obtainAccessToken(mockMvc, memberUsername, memberPassword);;
		invalidToken = memberToken + "xpto";
	}

	@Test
	public void insertShouldReturnUnauthorizedWhenNotValidToken() throws Exception {

		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setText("Gostei do filme!");
		reviewDTO.setMovieId(1L);

		String jsonBody = objectMapper.writeValueAsString(reviewDTO);
		
		ResultActions result =
				mockMvc.perform(post("/reviews")
						.content(jsonBody)
						.header("Authorization", "Bearer " + invalidToken)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void insertShouldReturnForbiddenWhenVisitorAuthenticated() throws Exception {
		
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setText("Gostei do filme!");
		reviewDTO.setMovieId(1L);

		String jsonBody = objectMapper.writeValueAsString(reviewDTO);
		
		ResultActions result =
				mockMvc.perform(post("/reviews")
						.header("Authorization", "Bearer " + visitorToken)
						.content(jsonBody)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isForbidden());
	}
	
	@Test
	public void insertShouldInsertReviewWhenMemberAuthenticatedAndValidData() throws Exception {
		
		String reviewText = "Gostei do filme!";
		long movieId = 1L;
		
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setText(reviewText);
		reviewDTO.setMovieId(movieId);

		String jsonBody = objectMapper.writeValueAsString(reviewDTO);
		
		ResultActions result =
				mockMvc.perform(post("/reviews")
						.header("Authorization", "Bearer " + memberToken)
						.content(jsonBody)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isCreated());
		
		result.andExpect(jsonPath("$.id").isNotEmpty());
		result.andExpect(jsonPath("$.text").value(reviewText));
		result.andExpect(jsonPath("$.movieId").value(movieId));
		
		result.andExpect(jsonPath("$.user").isNotEmpty());
		result.andExpect(jsonPath("$.user.id").isNotEmpty());
		result.andExpect(jsonPath("$.user.name").isNotEmpty());
		result.andExpect(jsonPath("$.user.email").value(memberUsername));
	}

	@Test
	public void insertShouldReturnUnproccessableEntityWhenMemberAuthenticatedAndInvalidData() throws Exception {
		
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setText("        ");
		reviewDTO.setMovieId(1L);

		String jsonBody = objectMapper.writeValueAsString(reviewDTO);

		ResultActions result =
				mockMvc.perform(post("/reviews")
						.header("Authorization", "Bearer " + memberToken)
						.content(jsonBody)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isUnprocessableEntity());
	}
}
