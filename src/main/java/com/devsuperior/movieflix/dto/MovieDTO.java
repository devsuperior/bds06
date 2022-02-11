package com.devsuperior.movieflix.dto;

import java.io.Serializable;

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

}
