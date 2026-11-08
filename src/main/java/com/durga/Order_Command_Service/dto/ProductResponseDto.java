package com.durga.Order_Command_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
	
	private String productCode;
	private String productName;
	private Integer avilableQuantity;
	private boolean avilable;

}

