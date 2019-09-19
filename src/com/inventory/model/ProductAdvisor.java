package com.inventory.model;

import java.util.ArrayList;
import java.util.List;

public class ProductAdvisor {
	public List<String> getProducts(String productCategory) {
		List<String> products = new ArrayList<String>();

		if (productCategory != null) {
			switch (productCategory.strip().toLowerCase()) {
			case "grocery":
				products.add("Enfamil");
				products.add("Kraft Dinner");
				break;
			case "sports":
				products.add("Fitness Tracker");
				products.add("Basketball");
				break;
			default:
				products.add("No products with category " + productCategory);
			}
		}

		return products;
	}
}
