package com.cognizant.cds.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cognizant.cds.model.ExceptionResponse;

/**
 * Exception resolver which translates system error messages to readable JSON
 * error messages
 * 
 * @author Raghavendra Hegde
 */

@ControllerAdvice
@RestController
public class ExceptionResolver extends ResponseEntityExceptionHandler {

	private static final String FAIL_FLAG = "FAILED";

	/**
	 * Handles InvalidInputException thrown when user enters an invalid type
	 * parameter to query
	 * 
	 * @param ex
	 * @param request
	 * @return ExceptionResponse
	 */
	@ExceptionHandler(InvalidInputException.class)
	public final ResponseEntity<ExceptionResponse> handleInvalidInputException(InvalidInputException ex,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(FAIL_FLAG, "err-001", ex.getMessage(),
				((ServletWebRequest) request).getRequest().getRequestURI().toString());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles NoHandlerFoundException thrown when resource is not found
	 * 
	 * @param ex
	 * @param headers
	 * @param status
	 * @param request
	 * @return ExceptionResponse
	 */
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(FAIL_FLAG, "err-002",
				"Resource not found", ex.getRequestURL());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	/**
	 * General Exception handler to handle all type of unhandled exceptions by other
	 * handlers
	 * 
	 * @param ex
	 * @param request
	 * @return ExceptionResponse
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse errorResponse = new ExceptionResponse(FAIL_FLAG, "err-003", ex.getMessage(),
				((ServletWebRequest) request).getRequest().getRequestURI().toString());
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}