package com.xpandit.movie_service.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author Flavio Leite
 *
 */
@Entity
@Table(name="movie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	@NotBlank(message = "The title cannot be null or empty.")
	private String title;
	@NotNull(message = "The launch date cannot be null.")
	private LocalDate launchDate;
	@Min(value = 0, message = "The rank must be at least 10")
    @Max(value = 10, message = "The rank must be at most 10")
	private int rank;
	@NotNull(message = "The revenue cannot be null.")
	private BigDecimal revenue;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(LocalDate launchDate) {
		this.launchDate = launchDate;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public BigDecimal getRevenue() {
		return revenue;
	}

	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}
	
	public Movie() {
		super();
	}

	public Movie(Integer id, @NotBlank(message = "The title cannot be null or empty.") String title,
			@NotNull(message = "The launch date cannot be null.") LocalDate launchDate,
			@Min(value = 0, message = "The rank must be at least 10") @Max(value = 10, message = "The rank must be at most 10") int rank,
			@NotNull(message = "The revenue cannot be null.") BigDecimal revenue) {
		this.id = id;
		this.title = title;
		this.launchDate = launchDate;
		this.rank = rank;
		this.revenue = revenue;
	}
	
}
