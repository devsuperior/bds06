package com.devsuperior.movieflix.entities.projection;

public interface ReviewProjection {

	Long getId();
	String getText();
	Long getMovieId();
	Long getUserId();
	String getName();
	String getEmail();
}
