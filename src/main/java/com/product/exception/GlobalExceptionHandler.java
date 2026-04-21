package com.product.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.product.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse> handleDtovalidationException(MethodArgumentNotValidException ex) {
		ApiResponse apiResponse = ApiResponse.builder().serviceName("PRODUCT_SERVICE").status(false).type("string")
				.payload(ex.getBindingResult().getFieldErrors().stream().map(error -> error.getDefaultMessage())
						.findFirst().orElse("Validation Error"))
				.build();
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// general Exception handler
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse> generalExceptionHandler(Exception ex) {
		ApiResponse apiResponse = ApiResponse.builder().serviceName("PRODUCT_SERVICE").status(false).type("string")
				.payload(((BindException) ex).getBindingResult().getFieldErrors().stream()
						.map(error -> error.getDefaultMessage()).findFirst().orElse("Validation Error"))
				.build();
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ApiResponse> noSuchElementExceptionHandler(NoSuchElementException ex) {
		ApiResponse apiResponse = ApiResponse.builder().serviceName("PRODUCT_SERVICE").status(false).type("string")
				.payload(ex.getMessage()).build();
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiResponse> runtimeExceptionHandler(RuntimeException ex) {

		ApiResponse apiResponse = ApiResponse.builder().serviceName("PRODUCT_SERVICE").status(false).type("string")
				.payload(ex.getMessage()).build();

		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}

}
