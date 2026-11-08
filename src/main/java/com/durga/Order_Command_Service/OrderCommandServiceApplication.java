package com.durga.Order_Command_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderCommandServiceApplication {

	/**
	 * Starts the order command application and initializes its Spring context.
	 *
	 * @param args command-line arguments passed to Spring Boot
	 */
	public static void main(String[] args) {
		SpringApplication.run(OrderCommandServiceApplication.class, args);
	}

}
