package com.inventory.data;

import static com.inventory.data.InventorySql.INSERT_INVENTORY_PRODUCT;
import static com.inventory.data.InventorySql.SELECT_INVENTORY;
import static com.inventory.data.InventorySql.SELECT_INVENTORY_BY_ID;
import static com.inventory.data.InventorySql.SELECT_INVENTORY_BY_NAME;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.inventory.model.Category;
import com.inventory.model.InventoryItem;

enum InventoryProductTable 
{
	InventoryProductTable();
	static final String ID = "id";
	static final String NAME = "name";
	static final String QUANTITY = "quantity";
	static final String CATEGORY_ID = "category_id";
	static final String CREATED_ON = "created_on";
}

public class InventoryDataImplementation {
	private Connection connection;
	private CategoryDataImplementation categoryData;
	
	public InventoryDataImplementation(Connection connection) {
		this.connection = connection;
		this.categoryData = new CategoryDataImplementation(connection);
	}
	
	public List<InventoryItem> getInventoryItemByName(String itemName) throws SQLException {
		PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_INVENTORY_BY_NAME);
		preparedStatement.setString(1, itemName);
		ResultSet result = preparedStatement.executeQuery();
		preparedStatement.clearParameters();
		
		List<InventoryItem> items = new ArrayList<InventoryItem>();
		while(result.next()) {
			InventoryItem item = new InventoryItem();
			item.setId((UUID)result.getObject(InventoryProductTable.ID));
			item.setName(result.getString(InventoryProductTable.NAME));
			item.setQuantity(result.getInt(InventoryProductTable.QUANTITY));
			Category category = categoryData.getCategoryById((UUID)result.getObject(InventoryProductTable.CATEGORY_ID));
			item.setCategory(category); 
		}
		return items;
	}
	
	public InventoryItem getInventoryItemById(UUID itemId) throws SQLException {
		PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_INVENTORY_BY_ID);
		preparedStatement.setObject(1, itemId, java.sql.Types.OTHER);
		ResultSet result = preparedStatement.executeQuery();
		preparedStatement.clearParameters();
		
		InventoryItem item = new InventoryItem();
		while(result.next()) { 
			item.setId((UUID)result.getObject(InventoryProductTable.ID));
			item.setName(result.getString(InventoryProductTable.NAME));
			item.setQuantity(result.getInt(InventoryProductTable.QUANTITY));
			Category category = categoryData.getCategoryById((UUID)result.getObject(InventoryProductTable.CATEGORY_ID));
			item.setCategory(category); 
		}
		return item;
	}
	
	public List<InventoryItem> getInventory() throws SQLException {
		int limit = 8;
		int currentPage = 1;
		int offset = (currentPage - 1) * limit;
		PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_INVENTORY);
		preparedStatement.setInt(1, limit);
		preparedStatement.setInt(2, offset); 
		ResultSet result = preparedStatement.executeQuery(); 
		preparedStatement.clearParameters();
		
		List<InventoryItem> items = new ArrayList<InventoryItem>();
		while(result.next()) {
			InventoryItem item = new InventoryItem();
			item.setId((UUID)result.getObject(InventoryProductTable.ID));
			item.setName(result.getString(InventoryProductTable.NAME));
			item.setQuantity(result.getInt(InventoryProductTable.QUANTITY));
			Category category = categoryData.getCategoryById((UUID)result.getObject(InventoryProductTable.CATEGORY_ID));
			item.setCategory(category); 
			items.add(item);
		}
		return items;
	}
	
	public UUID insertInventoryItem(InventoryItem item) throws SQLException {
		String returnColumnName[] = new String[] { InventoryProductTable.ID };
		PreparedStatement preparedStatement = this.connection.prepareStatement(INSERT_INVENTORY_PRODUCT, returnColumnName);
		preparedStatement.setString(1, item.getName());
		preparedStatement.setInt(2, item.getQuantity());
		preparedStatement.setObject(3, item.getCategory().getId(), java.sql.Types.OTHER); 
		
		UUID itemId = null;
		if (preparedStatement.executeUpdate() > 0) 
		{
			ResultSet result = preparedStatement.getGeneratedKeys();
			if (result.next())
			{
				itemId = (UUID)result.getObject(1);
			}
		}
		
		preparedStatement.clearParameters();  
		return itemId;
	}
}
