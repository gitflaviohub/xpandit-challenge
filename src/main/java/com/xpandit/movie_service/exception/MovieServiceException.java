package com.xpandit.movie_service.exception;

/**
 * @author Flavio Leite
 *
 */
public class MovieServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MovieServiceException(String message) {
		super(message);
	}

	public MovieServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
