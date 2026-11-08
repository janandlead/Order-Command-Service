package com.durga.Order_Command_Service.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.durga.Order_Command_Service.client.InventoryClient;
import com.durga.Order_Command_Service.dto.OrderRequest;
import com.durga.Order_Command_Service.dto.OrderResponse;
import com.durga.Order_Command_Service.dto.ProductResponseDto;
import com.durga.Order_Command_Service.entity.Order;
import com.durga.Order_Command_Service.exception.InsufficientStockException;
import com.durga.Order_Command_Service.exception.InventoryServiceUnavailableException;
import com.durga.Order_Command_Service.repository.OrderRepository;

import feign.FeignException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final InventoryClient inventoryClient;

	@Override
	public OrderResponse placeOrder(OrderRequest orderRequest) {

		ProductResponseDto productResponseDto;

		try {
			productResponseDto = inventoryClient.checkStock(orderRequest.getProductCode(), orderRequest.getQuantity());
		} catch (FeignException fiException) {

			if (fiException.status() == 404) {
				throw new InsufficientStockException("Product not found: " + orderRequest.getProductCode());
			}

			throw new InventoryServiceUnavailableException("Inventory service is currently unavailable ", fiException);
		}

		if (!productResponseDto.isAvilable()) {
			throw new InsufficientStockException("Insufficient stock for product: " + orderRequest.getProductCode());
		}

		Order order = new Order(productResponseDto.getProductCode(), 
				productResponseDto.getProductName(),
				LocalDateTime.now(), 
				productResponseDto.getAvilableQuantity());
		Order savedOrder = orderRepository.save(order);

		return toResdponse(savedOrder);

	}

	private OrderResponse toResdponse(Order order) {
		return new OrderResponse(order.getId(), order.getProductCode(), order.getQuantity(), order.getCreatedAt());
	}

}
