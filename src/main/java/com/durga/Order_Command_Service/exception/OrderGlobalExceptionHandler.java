package com.durga.Order_Command_Service.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.durga.Order_Command_Service.dto.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class OrderGlobalExceptionHandler {

	/**
	 * Maps missing products and insufficient stock to the application's HTTP 404 response.
	 *
	 * @param productNotFoundException business exception containing the failure message
	 * @param httpServletRequest request used to populate the error path
	 * @return HTTP 404 response with the timestamp, status, message, and request path
	 */
	@ExceptionHandler(InsufficientStockException.class)
	public ResponseEntity<ErrorResponse> handleProductNotFoundException(
			InsufficientStockException productNotFoundException, HttpServletRequest httpServletRequest) {

		ErrorResponse errorResponse = ErrorResponse.builder().timestamp(LocalDateTime.now())
				.status(HttpStatus.NOT_FOUND.value()).error(HttpStatus.NOT_FOUND.getReasonPhrase())
				.message(productNotFoundException.getMessage()).path(httpServletRequest.getRequestURI()).build();

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
}
