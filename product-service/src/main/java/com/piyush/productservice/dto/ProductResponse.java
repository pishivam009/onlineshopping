package com.piyush.productservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
	
	//separate model and dto entities
	//Good practice. If model changes, don't want it exposed
	private String id;
	private String name;
	private String description;
	private BigDecimal price;
}
