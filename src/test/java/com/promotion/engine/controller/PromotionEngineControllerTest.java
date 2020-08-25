package com.promotion.engine.controller;

import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.promotion.engine.domain.Product;
import com.promotion.engine.service.ProductService;

class PromotionEngineControllerTest {

	PromotionEngineController controller;

	@Mock
	ProductService service;

	@Mock
	Product product;

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	/**
	 * Test method for testCalculatePrice()
	 */
	@Test
	final void testCalculatePrice() {
		List<Product> products = new ArrayList<Product>();
		Double totalPrice = 100.00;

		Product productA = new Product("A", 50);
		Product productB = new Product("B", 30);
		Product productC = new Product("C", 20);
		Product productD = new Product("D", 15);
		products.add(productA);
		products.add(productB);
		products.add(productC);
		products.add(productD);

		List<Product> spy = Mockito.spy(products);

		ResponseEntity response = new ResponseEntity(totalPrice, HttpStatus.OK);

		PromotionEngineController controller = Mockito.mock(PromotionEngineController.class);
		when(controller.calculatePrice(products)).thenReturn(response);

	}

}
