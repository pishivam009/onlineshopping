package com.piyush.inventoryservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.piyush.inventoryservice.repository.InventoryRepository;

@Service
public class InventoryService {

	
	@Autowired
	InventoryRepository inventoryRepository;
	
	@Transactional(readOnly = true)
	public boolean isInStock(String skucode) {
		//chekcs if object is present or not
		
		return inventoryRepository.findBySkucode(skucode).isPresent();
	}
	
}
