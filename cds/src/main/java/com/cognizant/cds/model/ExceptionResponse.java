package com.cognizant.cds.model;

/**
 * This class is used to wrap and send more understandable error messages to end
 * users
 * 
 * @author Raghavendra Hegde
 */

public class ExceptionResponse {
	private String status;
	private String statusCode;
	private String message;
	private String uri;

	public ExceptionResponse(String status, String statusCode, String message, String uri) {
		this.status = status;
		this.statusCode = statusCode;
		this.message = message;
		this.uri = uri;
	}

	public String getStatus() {
		return status;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public String getMessage() {
		return message;
	}

	public String getUri() {
		return uri;
	}

}