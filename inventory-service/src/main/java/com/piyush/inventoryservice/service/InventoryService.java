package com.piyush.inventoryservice.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.piyush.inventoryservice.dto.InventoryResponse;
import com.piyush.inventoryservice.repository.InventoryRepository;

@Service
public class InventoryService {

	@Autowired
	InventoryRepository inventoryRepository;

	@Transactional(readOnly = true)
	public List<InventoryResponse> isInStock(List<String> skucodes) {
		// chekcs if object is present or not

		return inventoryRepository.findBySkucodeIn(skucodes).stream().map(inventory -> InventoryResponse.builder()
				.skucode(inventory.getSkucode()).isInStock(inventory.getQuantity() > 0).build()).toList();
	}

}
