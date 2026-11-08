package com.durga.Order_Command_Service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.durga.Order_Command_Service.dto.ProductResponseDto;

@FeignClient(name = "Inventory-Query-Service", url = "${inventory.service.url}")
public interface InventoryClient {

	@GetMapping("/api/products/{productCode}")
	ProductResponseDto checkStock(@PathVariable("productCode") String productCode,
			@RequestParam("quantity") Integer quantity);
}
