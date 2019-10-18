package com.inventory.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.inventory.model.Category;
import com.inventory.model.InventoryItem;

public class InventoryDataImplementation {
	private Connection connection;
	private CategoryDataImplementation categoryData;
	
	public InventoryDataImplementation(Connection connection) {
		this.connection = connection;
		this.categoryData = new CategoryDataImplementation(connection);
	}
	
	public List<InventoryItem> getInventoryItemByName(String itemName) throws SQLException { 
		String sql = "SELECT id, name, category_id, quantity FROM public.inv_products WHERE name = ?";
		PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
		preparedStatement.setString(1, itemName);
		ResultSet result = preparedStatement.executeQuery();
		preparedStatement.clearParameters();
		
		List<InventoryItem> items = new ArrayList<InventoryItem>();
		while(result.next()) {
			InventoryItem item = new InventoryItem();
			item.setId((UUID)result.getObject("id"));
			item.setName(result.getString("name"));
			item.setQuantity(result.getInt("quantity"));
			Category category = categoryData.getCategoryById((UUID)result.getObject("category_id"));
			item.setCategory(category); 
		}
		return items;
	}
	
	public InventoryItem getInventoryItemById(UUID itemId) throws SQLException { 
		String sql = "SELECT id, name, category_id FROM public.inv_products WHERE name = ?";
		PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
		preparedStatement.setObject(1, itemId, java.sql.Types.OTHER);
		ResultSet result = preparedStatement.executeQuery();
		preparedStatement.clearParameters();
		
		InventoryItem item = new InventoryItem();
		while(result.next()) { 
			item.setId((UUID)result.getObject("id"));
			item.setName(result.getString("name"));
			item.setQuantity(result.getInt("quantity"));
			Category category = categoryData.getCategoryById((UUID)result.getObject("category_id"));
			item.setCategory(category); 
		}
		return item;
	}
	
	public List<InventoryItem> getInventory() throws SQLException { 
		String sql = "SELECT id, name, category_id FROM public.inv_products";
		PreparedStatement preparedStatement = this.connection.prepareStatement(sql); 
		ResultSet result = preparedStatement.executeQuery(); 
		
		List<InventoryItem> items = new ArrayList<InventoryItem>();
		while(result.next()) {
			InventoryItem item = new InventoryItem();
			item.setId((UUID)result.getObject("id"));
			item.setName(result.getString("name"));
			item.setQuantity(result.getInt("quantity"));
			Category category = categoryData.getCategoryById((UUID)result.getObject("category_id"));
			item.setCategory(category); 
		}
		return items;
	}
	
	public boolean insertInventoryItem(InventoryItem item) throws SQLException {
		String sql = "insert into inv_products (name, quantity, category_id) values (?, ?, ?)";
		PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
		preparedStatement.setString(1, item.getName());
		preparedStatement.setInt(2, item.getQuantity());
		preparedStatement.setObject(3, item.getCategory().getId(), java.sql.Types.OTHER); 
		preparedStatement.execute();
		preparedStatement.clearParameters(); 
		
		return preparedStatement.getUpdateCount() == 1;
	}
}
