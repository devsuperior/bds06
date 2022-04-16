package com.devsuperior.movieflix.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.movieflix.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

	List<Review> findByMovieId(Long id);

}
