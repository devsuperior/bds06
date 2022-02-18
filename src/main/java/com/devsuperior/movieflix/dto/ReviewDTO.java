package com.devsuperior.movieflix.dto;

import java.io.Serializable;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Long movieId;
	private String text;
	private UserDTO user;

	public ReviewDTO(Review review) {
		this.id = review.getId();
		this.movieId = review.getMovie().getId();
		this.text = review.getText();
		this.user = new UserDTO(review.getUser().getId(), review.getUser().getName(), review.getUser().getEmail());
	}

	public Review toEntity(ReviewDTO dto) {
		Movie movie = new Movie();
		movie.setId(dto.getMovieId());

		com.devsuperior.movieflix.entities.User user = new com.devsuperior.movieflix.entities.User();
		user.setId(dto.getUser().getId());
		user.setName(dto.getUser().getName());
		user.setEmail(dto.getUser().getEmail());

		Review review = new Review(dto.id, dto.getText(), movie, user);
		return review;
	}

}
