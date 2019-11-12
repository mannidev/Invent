package com.inventory.data;

final class ProductCategorySql {
	static final String SELECT_PRODUCT_CATEGORY_BY_ID = ""
			+ "SELECT id, name "
			+ "FROM inv_product_categories "
			+ "WHERE id = ?";
	
	static final String SELECT_PRODUCT_CATEGORY_BY_NAME = ""
			+ "SELECT id, name "
			+ "FROM inv_product_categories "
			+ "WHERE name = ?";
	
	static final String INSERT_PRODUCT_CATEGORY = ""
			+ "INSERT INTO inv_product_categories (name) VALUES (?)";
}
