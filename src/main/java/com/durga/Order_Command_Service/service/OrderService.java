package com.durga.Order_Command_Service.service;

import com.durga.Order_Command_Service.dto.OrderRequest;
import com.durga.Order_Command_Service.dto.OrderResponse;

public interface OrderService {
	
	
	/**
	 * Checks inventory availability and persists an order when the product is available.
	 *
	 * @param orderRequest product code and quantity to check with inventory
	 * @return saved order identifier, product code, stored quantity, and creation time
	 * @throws com.durga.Order_Command_Service.exception.InsufficientStockException
	 *         if the product is missing or inventory reports it unavailable
	 * @throws com.durga.Order_Command_Service.exception.InventoryServiceUnavailableException
	 *         if another inventory client failure occurs
	 */
	public OrderResponse placeOrder(OrderRequest orderRequest);

}
