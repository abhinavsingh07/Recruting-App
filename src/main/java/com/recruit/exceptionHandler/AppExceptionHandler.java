package com.recruit.exceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global Exception Handler.Handles All Application Errors
 * 
 * @author Abhinav Singh
 *
 */
@EnableWebMvc // enables this controller advice
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	public AppExceptionHandler() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Handles Resource Not Found Exception Throws From App.
	 * 
	 * @param exception Exception Object
	 * @return ResponseEntity Error Response
	 */
	@ResponseBody
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(ResourceNotFoundException exception) {
		ErrorResponse error = new ErrorResponse();
		error.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles Bean And PathParam Validation Exceptions.
	 * 
	 * @param ex      ConstraintViolationException Object
	 * @param request Request To The App.
	 * @return ResponseEntity ErrorResponse With Details Array.
	 */
	@ResponseBody
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex,
			WebRequest request) {
		List<String> details = ex.getConstraintViolations().parallelStream().map(e -> e.getMessage())
				.collect(Collectors.toList());

		ErrorResponse error = new ErrorResponse();
		error.setMessage("BAD REQUEST");
		error.setDetails(details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	/**
	 * For All Other Exceptions
	 * 
	 * @param exception Exception Object
	 * @return ResponseEntity Error Response
	 */
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception exception) {
		ErrorResponse error = new ErrorResponse();
		error.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
