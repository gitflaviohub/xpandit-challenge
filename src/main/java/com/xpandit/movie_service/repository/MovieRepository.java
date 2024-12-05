/**
 * 
 */
package com.xpandit.movie_service.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xpandit.movie_service.domain.model.Movie;

/**
 * @author Flavio Leite
 *
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
	List<Movie> findByLaunchDate(LocalDate launchDate);
}
