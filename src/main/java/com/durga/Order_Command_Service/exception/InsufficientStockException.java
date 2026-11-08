package com.durga.Order_Command_Service.exception;

public class InsufficientStockException extends RuntimeException{

	public InsufficientStockException(String message) {
		super(message);
	}
}
