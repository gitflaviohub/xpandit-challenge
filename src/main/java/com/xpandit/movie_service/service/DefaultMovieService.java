package com.xpandit.movie_service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpandit.movie_service.domain.model.Movie;
import com.xpandit.movie_service.exception.MovieServiceException;
import com.xpandit.movie_service.repository.MovieRepository;

/**
 * @author Flavio Leite
 *
 */
@Service
public class DefaultMovieService implements MovieService {

	@Autowired
	private MovieRepository movieRepository;

	/**
	 *
	 */
	@Override
	public Movie save(final Movie movie) {
		return movieRepository.save(movie);
	}

	/**
	 *
	 */
	@Override
	public void delete(final Integer id) {
		movieRepository.deleteById(id);
	}

	/**
	 *
	 */
	@Override
	public Movie update(final Integer id, final Movie movieDetails) {
		Movie movie = movieRepository.findById(id)
				.orElseThrow(() -> new MovieServiceException("Movie not found withh id " + id));

		movie.setTitle(movieDetails.getTitle());
		movie.setLaunchDate(movieDetails.getLaunchDate());
		movie.setRevenue(movieDetails.getRevenue());

		return movieRepository.save(movie);
	}

	/**
	 *
	 */
	@Override
	public Optional<Movie> findById(Integer id) {
		return movieRepository.findById(id);
	}

	/**
	 *
	 */
	@Override
	public List<Movie> findAll() {
		return movieRepository.findAll();
	}

	/**
	 * @param launchDate
	 * @return
	 */
	public List<Movie> findByLaunchDate(String launchDate) {
		LocalDate date = LocalDate.parse(launchDate);
		return movieRepository.findByLaunchDate(date);
	}

	/**
	 * @param movies
	 * @return
	 */
	public List<Movie> saveAll(List<Movie> movies) {
		return movieRepository.saveAll(movies);
	}

	
	/**
	 * 
	 */
	public void deleteAll() {
		movieRepository.deleteAll();
	}
}
