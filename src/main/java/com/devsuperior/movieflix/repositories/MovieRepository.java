package com.devsuperior.movieflix.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.projection.MovieProjection;
import com.devsuperior.movieflix.entities.projection.ReviewProjection;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	@Query(value = "SELECT tm.id, tm.title, tm.sub_title AS subTitle, tm.year, tm.img_url AS imgUrl, tm.synopsis, tg.id AS idGenre , tg.name "
			+ "FROM tb_movie tm " + "LEFT OUTER JOIN tb_genre tg ON (tm.genre_id = tg.id) "
			+ "WHERE tm.id = :id ", nativeQuery = true)
	public Optional<MovieProjection> findByIdd(Long id);

	@Query(value = "SELECT tm.id, tm.title, tm.sub_title, tm.year, tm.img_url, tm.synopsis, tm.genre_id "
			+ "FROM tb_movie tm "
			+ "WHERE CASE WHEN COALESCE(:genreId, 0) = 0 THEN 1=1 ELSE tm.genre_id  = :genreId END "
			+ "ORDER BY tm.title ", nativeQuery = true)
	public Page<Movie> findByGenre(Integer genreId, Pageable pageable);

	@Query(value = "SELECT tr.id, tr.text, tr.movie_id AS movieId, tr.user_id AS userId, tu.name, tu.email "
			+ "FROM tb_review tr "
			+ "INNER JOIN tb_user tu ON (tr.user_id = tu.id) "
			+ "WHERE tr.id = :id "
			+ "", nativeQuery = true)
	public Optional<ReviewProjection> findByReview(Long id);
		
}
