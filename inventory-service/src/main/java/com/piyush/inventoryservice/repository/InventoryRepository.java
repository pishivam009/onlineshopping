package com.piyush.inventoryservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.piyush.inventoryservice.model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	
	Optional<Inventory> findBySkucode(String skucode);

	List<Inventory> findBySkucodeIn(List<String> skucode);
	

}
