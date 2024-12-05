package com.xpandit.movie_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xpandit.movie_service.domain.model.Movie;
import com.xpandit.movie_service.service.DefaultMovieService;

import jakarta.validation.Valid;

/**
 * @author Flavio Leite
 *
 */
@RestController
@RequestMapping("/api")
public class MovieController {

	private final DefaultMovieService movieService;

	@Autowired
	public MovieController(DefaultMovieService movieService) {
		this.movieService = movieService;
	}

	@GetMapping("/movie")
	public List<Movie> getAllMovies() {
		return movieService.findAll();
	}

	@GetMapping("/movie/filter")
	public List<Movie> getMoviesByLaunchDate(@RequestParam String launchDate) {
		return movieService.findByLaunchDate(launchDate);
	}

	@GetMapping("/movie/{id}")
	public ResponseEntity<Movie> getMovieById(@PathVariable Integer id) {
		return movieService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/movie")
	public Movie createMovie(@Valid @RequestBody Movie movie) {
		return movieService.save(movie);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/movie/collection")
	public List<Movie> createMovies(@RequestBody List<Movie> movies) {
		return movieService.saveAll(movies);
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@PutMapping("/movie/{id}")
	public ResponseEntity<Movie> updateMovie(@PathVariable Integer id, @Valid @RequestBody Movie movieDetails) {
		Movie updatedMovie = movieService.update(id, movieDetails);
		return ResponseEntity.ok(updatedMovie);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/movie/{id}")
	public void deleteMovie(@PathVariable Integer id) {
		movieService.delete(id);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/movie/all")
	public void deleteAll() {
		movieService.deleteAll();
	}
}
