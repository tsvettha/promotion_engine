package com.promotion.engine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promotion.engine.domain.Product;
import com.promotion.engine.service.ProductService;


@RestController
@RequestMapping("/products")
public class PromotionEngineController {
	@Autowired
	private ProductService productService;

	@PostMapping("/price")
	public ResponseEntity<Double> calculatePrice(@RequestBody List<Product> items) {
		Double totalPrice = productService.calculatePrice(items);
		return new ResponseEntity<Double>(totalPrice, HttpStatus.OK);
	}
}
