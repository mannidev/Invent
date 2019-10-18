package com.inventory.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;

import com.inventory.data.CategoryDataImplementation;
import com.inventory.data.InventoryDataImplementation;
import com.inventory.model.Category;
import com.inventory.model.InventoryItem;

public class InventoryService {
	private ServletContext servletContext;
	private Connection connection;
	private InventoryDataImplementation inventoryDataProvider;
	private CategoryDataImplementation catagoryDataProvider;
	
	public InventoryService(ServletContext context) 
	{
		this.servletContext = context;
		this.connection = (Connection) servletContext.getAttribute("dbConnection");
		this.inventoryDataProvider = new InventoryDataImplementation(connection);
		this.catagoryDataProvider = new CategoryDataImplementation(connection);
	}
	
	public boolean CreateNewInventoryItem(InventoryItem inventoryItem) 
	{ 
		try 
		{
			String categoryName = inventoryItem.getCategory().getName(); 
			Category category = this.catagoryDataProvider.getCategoryByName(categoryName);
			 
			if (category.getId() == null) {
				catagoryDataProvider.insertCategory(inventoryItem.getCategory());
				category = this.catagoryDataProvider.getCategoryByName(categoryName); 
			}
			
			inventoryItem.setCategory(category); 
			return inventoryDataProvider.insertInventoryItem(inventoryItem); 
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return false;
	}
	 
	
	 
}
