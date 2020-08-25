/**
 * 
 */
package com.promotion.engine.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.promotion.engine.domain.Product;
import com.promotion.engine.service.ProductService;

class PromotionEngineControllerTest {
	
	PromotionEngineController controller;
	ProductService service;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		service = new ProductService();
	}

	/**
	 * Test method for {@link com.promotion.engine.controller.PromotionEngineController#calculatePrice(java.util.List)}.
	 */
	@Test
	final void testCalculatePrice() {
		List<Product> products = new ArrayList<Product>();
		
		Product productA = new Product("A", 50);
		Product productB = new Product("B", 30);
		Product productC = new Product("C", 20);
		Product productD = new Product("D", 15);
		products.add(productA);
		products.add(productB);
		products.add(productC);
		products.add(productD);
		
		
		Mockito.when(service.calculatePrice(products));
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/products/price");
		ResponseEntity response = controller.calculatePrice(products);
		
	}

}
