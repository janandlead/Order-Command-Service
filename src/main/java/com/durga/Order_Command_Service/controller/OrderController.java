package com.durga.Order_Command_Service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.durga.Order_Command_Service.dto.OrderRequest;
import com.durga.Order_Command_Service.dto.OrderResponse;
import com.durga.Order_Command_Service.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@PostMapping
	public OrderResponse placeOrder(@RequestBody OrderRequest orderRequest) {

		return orderService.placeOrder(orderRequest);
	}

}
