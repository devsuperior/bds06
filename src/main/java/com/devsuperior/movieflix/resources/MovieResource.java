package com.devsuperior.movieflix.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.projection.MovieProjection;
import com.devsuperior.movieflix.services.MovieService;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {

	@Autowired
	private MovieService movieService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Movie> findById(@PathVariable Long id) {

		Optional<MovieProjection> movieOptional = movieService.findById(id);
		if (movieOptional.isPresent()) {

			Movie movie = new Movie(Long.valueOf(movieOptional.get().getId()), movieOptional.get().getTitle(),
					movieOptional.get().getSubTitle(), movieOptional.get().getSynopsis(),
					Integer.valueOf(movieOptional.get().getYear()), movieOptional.get().getImgUrl(),
					new Genre(Long.valueOf(movieOptional.get().getIdGenre()), movieOptional.get().getName(), null),
					null);

			return new ResponseEntity<Movie>(movie, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
