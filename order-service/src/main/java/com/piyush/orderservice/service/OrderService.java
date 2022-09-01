package com.piyush.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.piyush.orderservice.dto.InventoryResponse;
import com.piyush.orderservice.dto.OrderLineItemsDto;
import com.piyush.orderservice.dto.OrderRequest;
import com.piyush.orderservice.model.Order;
import com.piyush.orderservice.model.OrderLineItems;
import com.piyush.orderservice.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class OrderService {
	
	@Autowired
	WebClient.Builder webClientBuilder;
	
	@Autowired
	OrderRepository orderRepository;

	public void placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());

		//orderRequest.getOrderLineItemsDto().stream().map(orderLineItemsDto -> mapToDto(orderLineItemsDto));
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDto().stream().map(this::mapToDto).toList();
		order.setOrderLineItems(orderLineItems);
		//List all the sku codes together
		List<String> skucodes = order.getOrderLineItems().stream().map(orderLineItem -> orderLineItem.getSkuCode()).toList();
		//Call inventory service and place order if is in stock
		//format it in inventory?skucode=a&skucode=b type
		InventoryResponse[] inventoryResposeArray = webClientBuilder.build().get()
						.uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skucodes).build())
						.retrieve().bodyToMono(InventoryResponse[].class)
						.block();
		//block() for synchronous
		boolean allProductsInStock = Arrays.stream(inventoryResposeArray).allMatch(inventoryResponse -> inventoryResponse.isInStock());
		if(allProductsInStock) {
			log.info("All products are in stock");
			orderRepository.save(order);}
		else {
			throw new IllegalArgumentException("All products not in stock. Please Try again later");
		}
	}

	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

		return orderLineItems;
	}
}
