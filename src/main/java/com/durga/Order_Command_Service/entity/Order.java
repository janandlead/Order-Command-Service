package com.durga.Order_Command_Service.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="orders")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	
	public Order(String productCode, String productName, LocalDateTime createdAt, Integer quantity) {
		// TODO Auto-generated constructor stub
		this.productCode=productCode;
		this.productName=productName;
		this.createdAt=createdAt;
		this.quantity=quantity;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="product_code")
	private String productCode;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	private Integer quantity;
	
}

