package com.durga.Order_Command_Service.exception;

public class InsufficientStockException extends RuntimeException{

	/**
	 * Creates an exception describing a missing product or unavailable stock.
	 *
	 * @param message explanation of why the order cannot be fulfilled
	 */
	public InsufficientStockException(String message) {
		super(message);
	}
}
