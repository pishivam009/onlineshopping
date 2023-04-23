package com.piyush.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.piyush.orderservice.dto.OrderRequest;
import com.piyush.orderservice.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@CircuitBreaker(name="inventory" , fallbackMethod = "fallbackMethod")
	//Circuit breaker pattern applied here
	@TimeLimiter(name = "inventory")
	//name should be same as the one used in application.properties
	public String placeOrder(@RequestBody OrderRequest orderRequest) {
		orderService.placeOrder(orderRequest);
		return "Order Placed Successfully";
	}
	
	//fallbackMethod should have same return type as method
	public String fallbackMethod(@RequestBody OrderRequest orderRequest, RuntimeException runtimeException) {
		return "Oops! Something went wrong. Please order after some time";
	}
	
}
