package com.durga.Order_Command_Service.exception;

import feign.FeignException;

public class InventoryServiceUnavailableException extends RuntimeException {

	/**
	 * Creates an inventory service failure without an underlying cause.
	 *
	 * @param message description of the inventory service failure
	 */
	public InventoryServiceUnavailableException(String message) {
		super(message);
	}

	/**
	 * Creates an inventory service failure while preserving the client exception.
	 *
	 * @param message description of the inventory service failure
	 * @param cause Feign exception that caused the inventory check to fail
	 */
	public InventoryServiceUnavailableException(String message, FeignException cause) {
		// TODO Auto-generated constructor stub
		super(message, cause);
	}
}
