package com.devsuperior.movieflix.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.projection.MovieProjection;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query(value = "SELECT tm.id, tm.title, tm.sub_title AS subTitle, tm.year, tm.img_url AS imgUrl, tm.synopsis, tg.id AS idGenre , tg.name " 
			+ "FROM tb_movie tm " 
			+ "LEFT OUTER JOIN tb_genre tg ON (tm.genre_id = tg.id) "
			+ "WHERE tm.id = :id ", nativeQuery = true)
	public Optional<MovieProjection> findByIdd(Long id);
}
