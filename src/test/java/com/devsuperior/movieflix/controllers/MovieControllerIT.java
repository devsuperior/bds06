package com.devsuperior.movieflix.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.devsuperior.movieflix.tests.TokenUtil;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MovieControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TokenUtil tokenUtil;

	private long existingId;
	private long nonExistingId;
	
	private String visitorUsername;
	private String visitorPassword;
	private String memberUsername;
	private String memberPassword;
	private String memberToken, visitorToken, invalidToken;
	
	@BeforeEach
	void setUp() throws Exception {

		existingId = 1L;
		nonExistingId = 100000L;
		
		visitorUsername = "bob@gmail.com";
		visitorPassword = "123456";
		memberUsername = "ana@gmail.com";
		memberPassword = "123456";
		visitorToken = tokenUtil.obtainAccessToken(mockMvc, visitorUsername, visitorPassword);
		memberToken = tokenUtil.obtainAccessToken(mockMvc, memberUsername, memberPassword);;
		invalidToken = memberToken + "xpto";
	}

	@Test
	public void findByIdShouldReturnUnauthorizedWhenNoTokenGiven() throws Exception {

		ResultActions result =
				mockMvc.perform(get("/movies/{id}", existingId)
						.header("Authorization", "Bearer " + invalidToken)
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isUnauthorized());
	}	

	@Test
	public void findByIdShouldReturnMovieWhenUserVisitorAuthenticated() throws Exception {
		
		ResultActions result =
				mockMvc.perform(get("/movies/{id}", existingId)
					.header("Authorization", "Bearer " + visitorToken)
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").value(existingId));
		result.andExpect(jsonPath("$.title").isNotEmpty());
		result.andExpect(jsonPath("$.subTitle").isNotEmpty());
		result.andExpect(jsonPath("$.publishedAt").isNotEmpty());
		result.andExpect(jsonPath("$.imgUrl").isNotEmpty());
		result.andExpect(jsonPath("$.synopsis").isNotEmpty());
		result.andExpect(jsonPath("$.genre").isNotEmpty());
		result.andExpect(jsonPath("$.genre.id").isNotEmpty());
		result.andExpect(jsonPath("$.genre.name").isNotEmpty());
	}

	@Test
	public void findByIdShouldReturnMovieWhenMemberAuthenticated() throws Exception {
		
		ResultActions result =
				mockMvc.perform(get("/movies/{id}", existingId)
					.header("Authorization", "Bearer " + memberToken)
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").value(existingId));
		result.andExpect(jsonPath("$.title").isNotEmpty());
		result.andExpect(jsonPath("$.subTitle").isNotEmpty());
		result.andExpect(jsonPath("$.publishedAt").isNotEmpty());
		result.andExpect(jsonPath("$.imgUrl").isNotEmpty());
		result.andExpect(jsonPath("$.synopsis").isNotEmpty());
		result.andExpect(jsonPath("$.genre").isNotEmpty());
		result.andExpect(jsonPath("$.genre.id").isNotEmpty());
		result.andExpect(jsonPath("$.genre.name").isNotEmpty());
	}

	@Test
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
		
		ResultActions result =
				mockMvc.perform(get("/movies/{id}", nonExistingId)
					.header("Authorization", "Bearer " + visitorToken)
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void findByGenreShouldReturnUnauthorizedWhenNoTokenGiven() throws Exception {

		ResultActions result =
				mockMvc.perform(get("/movies")
					.header("Authorization", "Bearer " + invalidToken)
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isUnauthorized());
	}

	@Test
	public void findByGenreShouldReturnOrderedPageWhenVisitorAuthenticated() throws Exception {

		ResultActions result =
				mockMvc.perform(get("/movies")
					.header("Authorization", "Bearer " + visitorToken)
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		
		result.andExpect(jsonPath("$.content[0].id").isNotEmpty());
		result.andExpect(jsonPath("$.content[0].title").value("A Voz do Silêncio"));
		result.andExpect(jsonPath("$.content[0].subTitle").isNotEmpty());
		result.andExpect(jsonPath("$.content[0].publishedAt").isNotEmpty());
		result.andExpect(jsonPath("$.content[0].imgUrl").isNotEmpty());

		result.andExpect(jsonPath("$.content[1].title").value("Bob Esponja"));
		result.andExpect(jsonPath("$.content[2].title").value("Código de Conduta"));
		result.andExpect(jsonPath("$.content[3].title").value("Kingsman"));
		result.andExpect(jsonPath("$.content[4].title").value("O Labirinto do Fauno"));
	}

	@Test
	public void findByGenreShouldReturnOrderedPageWhenMemberAuthenticated() throws Exception {
		
		ResultActions result =
				mockMvc.perform(get("/movies")
					.header("Authorization", "Bearer " + memberToken)
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		
		result.andExpect(jsonPath("$.content[0].id").isNotEmpty());
		result.andExpect(jsonPath("$.content[0].title").value("A Voz do Silêncio"));
		result.andExpect(jsonPath("$.content[0].subTitle").isNotEmpty());
		result.andExpect(jsonPath("$.content[0].publishedAt").isNotEmpty());
		result.andExpect(jsonPath("$.content[0].imgUrl").isNotEmpty());

		result.andExpect(jsonPath("$.content[1].title").value("Bob Esponja"));
		result.andExpect(jsonPath("$.content[2].title").value("Código de Conduta"));
		result.andExpect(jsonPath("$.content[3].title").value("Kingsman"));
		result.andExpect(jsonPath("$.content[4].title").value("O Labirinto do Fauno"));
	}

	@Test
	public void findByGenreShouldReturnFilteredMoviesWhenGenreIsInformed() throws Exception {

		long genreId = 1L;
		
		ResultActions result =
				mockMvc.perform(get("/movies?genreId=" + genreId)
					.header("Authorization", "Bearer " + visitorToken)
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		
		result.andExpect(jsonPath("$.content[0].id").isNotEmpty());
		result.andExpect(jsonPath("$.content[0].title").value("Bob Esponja"));
		result.andExpect(jsonPath("$.content[0].subTitle").isNotEmpty());
		result.andExpect(jsonPath("$.content[0].publishedAt").isNotEmpty());
		result.andExpect(jsonPath("$.content[0].imgUrl").isNotEmpty());

		result.andExpect(jsonPath("$.content[1].title").value("Kingsman"));
		result.andExpect(jsonPath("$.content[2].title").value("Sonic"));
	}
}
