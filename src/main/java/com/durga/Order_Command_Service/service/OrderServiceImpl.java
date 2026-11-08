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

	/**
	 * Checks inventory and saves an order using the returned product details.
	 * The stored quantity is the inventory response's available quantity, rather
	 * than the request quantity. This operation does not reserve or decrement stock.
	 *
	 * @param orderRequest product code and requested quantity for the inventory check
	 * @return response mapped from the persisted order
	 * @throws InsufficientStockException if inventory returns HTTP 404 or reports
	 *         the product unavailable
	 * @throws InventoryServiceUnavailableException if another Feign failure occurs
	 */
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

	/**
	 * Maps a persisted order to the fields exposed by the order creation API.
	 *
	 * @param order saved order to map
	 * @return response containing the ID, product code, quantity, and creation time
	 */
	private OrderResponse toResdponse(Order order) {
		return new OrderResponse(order.getId(), order.getProductCode(), order.getQuantity(), order.getCreatedAt());
	}

}
