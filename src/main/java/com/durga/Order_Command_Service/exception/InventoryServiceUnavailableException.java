package com.durga.Order_Command_Service.exception;

import feign.FeignException;

public class InventoryServiceUnavailableException extends RuntimeException {

	public InventoryServiceUnavailableException(String message) {
		super(message);
	}

	public InventoryServiceUnavailableException(String message, FeignException cause) {
		// TODO Auto-generated constructor stub
		super(message, cause);
	}
}
