package com.durga.Order_Command_Service.service;

import com.durga.Order_Command_Service.dto.OrderRequest;
import com.durga.Order_Command_Service.dto.OrderResponse;

public interface OrderService {
	
	
	public OrderResponse placeOrder(OrderRequest orderRequest);

}
