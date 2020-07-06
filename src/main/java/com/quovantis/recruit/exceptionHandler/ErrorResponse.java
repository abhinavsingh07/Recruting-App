package com.quovantis.recruit.exceptionHandler;

import java.util.List;

/**
 * Error Response Structure
 * @author Abhinav Singh
 *
 */
public class ErrorResponse {

	private String message;
	private List<String> details;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

}
