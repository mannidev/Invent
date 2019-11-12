package com.inventory.data;

final class InventorySql {
	static final String SELECT_INVENTORY_BY_NAME = ""
			+ "SELECT id, name, category_id, quantity "
			+ "FROM public.inv_products "
			+ "WHERE name = ?";
	
	static final String SELECT_INVENTORY_BY_ID = ""
			+ "SELECT id, name, category_id, quantity "
			+ "FROM public.inv_products "
			+ "WHERE id = ?";
	
	static final String SELECT_INVENTORY = ""
			+ "SELECT id, name, category_id, quantity "
			+ "FROM public.inv_products "
			+ "LIMIT ? "
			+ "OFFSET ?";
	
	static final String INSERT_INVENTORY_PRODUCT = ""
			+ "INSERT into inv_products (name, quantity, category_id) "
			+ "VALUES (?, ?, ?)";
}
