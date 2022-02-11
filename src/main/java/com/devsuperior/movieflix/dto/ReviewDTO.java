package com.devsuperior.movieflix.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long movieId;
	private String text;

}
