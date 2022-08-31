package com.piyush.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piyush.productservice.dto.ProductRequest;
import com.piyush.productservice.dto.ProductResponse;
import com.piyush.productservice.model.Product;
import com.piyush.productservice.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
//@RequiredArgsConstructor
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	// or
	// private final ProductRepository productRepository;

	public void createProduct(ProductRequest productRequest) {
		Product product = Product.builder().name(productRequest.getName()).description(productRequest.getDescription())
				.price(productRequest.getPrice()).build();
		productRepository.save(product);
		// log.info("Product" + product.getId() +"is saved");
		log.info("Product {} is saved", product.getId());

	}
	
	public List<ProductResponse> getAllProducts(){
		List<Product> products = productRepository.findAll();
		//return products.stream().map(product -> mapToProductResponse(product)).toList();
		return products.stream().map(this:: mapToProductResponse).toList();
		
		
	}

	private ProductResponse mapToProductResponse(Product product) {
		// TODO Auto-generated method stub
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}
}
