package com.xpandit.movie_service.service;

import java.util.List;
import java.util.Optional;

import com.xpandit.movie_service.domain.model.Movie;

/**
 * @author Flavio Leite
 *
 */
public interface MovieService {
	List<Movie> findAll();

	Optional<Movie> findById(Integer id);

	Movie save(Movie movie);

	Movie update(Integer id, Movie movieDetails);

	void delete(Integer id);
}
