package com.piyush.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.piyush.inventoryservice.model.Inventory;
import com.piyush.inventoryservice.repository.InventoryRepository;

@SpringBootApplication
@EnableEurekaClient
@EnableWebMvc
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	
	//To load the data initially
	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args ->{
			Inventory inventory = new Inventory();
			inventory.setSkucode("iphone_13");
			inventory.setQuantity(100);
			

			Inventory inventory1 = new Inventory();
			inventory1.setSkucode("iphone_13_red");
			inventory1.setQuantity(0);
			
			
			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
		
	}

}
