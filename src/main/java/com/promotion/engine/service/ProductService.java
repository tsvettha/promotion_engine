package com.promotion.engine.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promotion.engine.domain.Product;
import com.promotion.engine.util.RulesUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	RulesUtil rulesUtil;

	Map<String, Double> productPriceMap;

	@PostConstruct
	public void init() {
		this.rulesUtil.initializeRules();
		loadProductPrice();
	}

	private void loadProductPrice() {
		productPriceMap = new HashMap<>();

		productPriceMap.put("A", 50.00);
		productPriceMap.put("B", 30.00);
		productPriceMap.put("C", 20.00);
		productPriceMap.put("D", 15.00);
	}

	/**
	 * Method to calculate item price based on the Item Sku Code and Quantity
	 * 
	 * @param products List of products supplied by user with Product Sku Code and
	 *                 quantiy
	 * @return
	 */
	public Double calculatePrice(List<Product> products) {
		// Fetch matching Rules
		fetchMatchingRulesAndCalculatePrice(products);

		// Calculate Total price
		products.parallelStream().forEach(product -> setPrice(product));
		Double totalPrice = products.parallelStream().collect(Collectors.summingDouble(Product::getTotalPrice));
		return totalPrice;
	}

	private void setPrice(Product product) {
		Double nonOfferQty = 0.00 + product.getReqQuantity();
		Double totalOfferPrice = 0.00;
		Double totalPrice = 0.00;
		
		if (product.getOfferQuantity() != null && product.getOfferQuantity() > 0) {
			Double offerOnQty = Math.floor(product.getReqQuantity() / product.getOfferQuantity());
			totalOfferPrice = offerOnQty * product.getOfferPrice();
			nonOfferQty = nonOfferQty - (offerOnQty * product.getOfferQuantity());
		}
		totalPrice = (nonOfferQty * productPriceMap.get(product.getSkuCode())) + totalOfferPrice;
		product.setTotalPrice(totalPrice);
	}

	private void fetchMatchingRulesAndCalculatePrice(List<Product> products) {
		int fired = this.rulesUtil.fireRules(products);
	}
}
