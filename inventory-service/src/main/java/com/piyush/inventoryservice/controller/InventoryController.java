package com.piyush.inventoryservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.piyush.inventoryservice.dto.InventoryResponse;
import com.piyush.inventoryservice.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

	
	@Autowired
	InventoryService inventoryService;
	
	//localhost:8083/api/inventory?skucode=iphone13&skucode=samsung
//	@GetMapping("/{skucode}")
//	@ResponseStatus(HttpStatus.OK)
//	public boolean isInStock(@PathVariable("skucode") String skucode) {
//		return inventoryService.isInStock(skucode);
//	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<InventoryResponse> isInStock(@RequestParam("skuCode") List<String> skucodes) {
		return inventoryService.isInStock(skucodes);
	}
}
