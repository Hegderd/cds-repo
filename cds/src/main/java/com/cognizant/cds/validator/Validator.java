package com.cognizant.cds.validator;

import java.util.regex.Pattern;

import com.cognizant.cds.util.Util;

/**
 * Validator class
 * 
 * @author Raghavendra Hegde
 */

public class Validator {
	private boolean error;
	private String errorMessage;

	public void validateSalary(final String salaryStr) {
		Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
		if (salaryStr == null || !pattern.matcher(salaryStr).matches()) {
			error = true;
			errorMessage = "Invalid request parameter";
		}
	}

	public boolean isError() {
		return error;
	}

	public String getErrorMessage() {
		return Util.trimString(errorMessage);
	}
}
