package com.devsuperior.movieflix.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.ReviewService;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewResource {

	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@PostMapping
	public ResponseEntity<ReviewDTO> insert(@RequestBody @Valid  ReviewDTO dto){
		ReviewDTO reviewDTO = reviewService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(reviewDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<ReviewDTO>> findAll(){
		List<Review> reviews = reviewRepository.findAll();
		return ResponseEntity.ok(reviews.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList()));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<List<ReviewDTO>> findById(@PathVariable Long id){
		List<ReviewDTO> reviews = reviewService.findReviewsMovieId(id);
		return ResponseEntity.ok(reviews);
	}

	
}
