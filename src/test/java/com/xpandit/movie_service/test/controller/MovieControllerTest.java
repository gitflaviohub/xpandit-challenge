package com.xpandit.movie_service.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.xpandit.movie_service.controller.MovieController;
import com.xpandit.movie_service.domain.model.Movie;
import com.xpandit.movie_service.service.DefaultMovieService;

/**
 * @author Flavio Leite
 *
 */
@WebMvcTest(MovieController.class)
public class MovieControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private DefaultMovieService movieService;

	@InjectMocks
	private MovieController movieController;

	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		movieService.deleteAll();
	}

	@Test
	public void testCreate() throws Exception {
		Movie movie = new Movie(1, "LOTR I", LocalDate.of(2001, 12, 19), 10, BigDecimal.valueOf(871000000));

		when(this.movieService.save(any(Movie.class))).thenReturn(movie);

		this.mockMvc
				.perform(post("/api/movie").contentType(MediaType.APPLICATION_JSON).content(
						"{\"title\":\"LOTR I\",\"launchDate\":\"2001-12-19\",\"rank\":10,\"revenue\":871000000}"))
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.title").value("LOTR I")).andExpect(jsonPath("$.launchDate").value("2001-12-19"))
				.andExpect(jsonPath("$.rank").value(10)).andExpect(jsonPath("$.revenue").value(871000000));

	}

	@Test
	public void testFindAll() throws Exception {
		List<Movie> movies = Arrays.asList(
				new Movie(1, "LOTR I", LocalDate.of(2001, 12, 19), 10, BigDecimal.valueOf(671000000)),
				new Movie(2, "LOTR II", LocalDate.of(2002, 12, 20), 10, BigDecimal.valueOf(871000000)),
				new Movie(3, "LOTR III", LocalDate.of(2003, 12, 17), 10, BigDecimal.valueOf(1071000000)));

		when(movieService.findAll()).thenReturn(movies);

		this.mockMvc.perform(get("/api/movie")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].title").value("LOTR I")).andExpect(jsonPath("$[1].title").value("LOTR II"))
				.andExpect(jsonPath("$[2].title").value("LOTR III"));
	}

	@Test
	public void testFindMovieById() throws Exception {
		Movie movie = new Movie(1, "LOTR III", LocalDate.of(2003, 12, 17), 10, BigDecimal.valueOf(1071000000));

		when(movieService.findById(1)).thenReturn(Optional.of(movie));

		mockMvc.perform(get("/api/movie/1")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.title").value("LOTR III"));
	}

	@Test
	public void testFindMovieById_NotFound() throws Exception {
		when(movieService.findById(100)).thenReturn(Optional.empty());

		mockMvc.perform(get("/api/movie/100")).andExpect(status().isNotFound());
	}

	@Test
	public void testUpdate() throws Exception {
		Movie movie = new Movie(1, "LOTR II", LocalDate.of(2002, 12, 20), 10, BigDecimal.valueOf(871000000));

		when(movieService.update(eq(1), any(Movie.class))).thenReturn(movie);

		mockMvc.perform(put("/api/movie/1").contentType(MediaType.APPLICATION_JSON)
				.content("{\"title\":\"LOTR II\",\"launchDate\":\"2002-12-20\",\"rank\":10,\"revenue\":871000000}"))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.title").value("LOTR II")).andExpect(jsonPath("$.rank").value("10"));
	}

	@Test
	public void testDelete() throws Exception {
		doNothing().when(movieService).delete(1);
		mockMvc.perform(delete("/api/movie/2")).andExpect(status().isNoContent());
	}

	@Test
	public void testDeleteAll() throws Exception {
		doNothing().when(movieService).deleteAll();
		mockMvc.perform(delete("/api/movie/all")).andExpect(status().isNoContent());
	}

	@Test
	public void testFindByLaunchDate() throws Exception {
		List<Movie> movies = Arrays.asList(
				new Movie(1, "LOTR I", LocalDate.of(2001, 12, 19), 10, BigDecimal.valueOf(671000000)),
				new Movie(2, "LOTR II", LocalDate.of(2002, 12, 20), 10, BigDecimal.valueOf(871000000)),
				new Movie(3, "LOTR III", LocalDate.of(2003, 12, 17), 10, BigDecimal.valueOf(1071000000)));

		when(movieService.findByLaunchDate("2001-12-19")).thenReturn(movies);

		mockMvc.perform(get("/api/movie/filter?launchDate=2001-12-19")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].title").value("LOTR I"));
	}
}
