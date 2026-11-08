package com.durga.Order_Command_Service.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

	private Long orderId;
	private String productCode;
	private Integer quantity;
	private LocalDateTime createdAt;
}
