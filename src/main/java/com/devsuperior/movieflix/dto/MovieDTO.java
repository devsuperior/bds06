package com.devsuperior.movieflix.dto;

import java.io.Serializable;

import com.devsuperior.movieflix.entities.Movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String title;
	private String subTitle;
	private String synopsis;
	private Integer year;
	private String imgUrl;
	private GenreDTO genreDTO;

	public MovieDTO(Movie movie) {
		this.id = movie.getId();
		this.title = movie.getTitle();
		this.subTitle = movie.getSubTitle();
		this.synopsis = movie.getSynopsis();
		this.year = movie.getYear();
		this.imgUrl = movie.getImgUrl();
		this.genreDTO = new GenreDTO(movie.getGenre().getId(), movie.getGenre().getName());
	}

}
