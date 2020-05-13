package com.cognizant.cds.exception;

/**
 * @author Raghavendra Hegde
 */

public class InvalidInputException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidInputException(String message) {
		super(message);
	}

}
