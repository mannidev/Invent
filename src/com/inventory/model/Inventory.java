package com.inventory.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest; 

public class Inventory 
{
	private ServletContext servletContext;
	private Connection connection;
	
	public Inventory(ServletContext context) 
	{
		this.servletContext = context;
		this.connection = (Connection) servletContext.getAttribute("dbConnection");
	}
	
	public boolean CreateNewInventoryItem(HttpServletRequest request) 
	{ 
		try 
		{
			String categoryName = request.getParameter("productCategory");
			Map<UUID, String> category = getCategory(categoryName);
			
			if (!category.containsValue(categoryName)) {
				category = insertCategory(categoryName);
			}
						
			String sql = "insert into inv_products (name, category_id) values (?, ?)";
			PreparedStatement insertProduct = this.connection.prepareStatement(sql);
			insertProduct.setString(1, request.getParameter("productName"));
			insertProduct.setObject(2, category.keySet().iterator().next(), java.sql.Types.OTHER);
			insertProduct.execute();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return false;
	}
	
	private Map<UUID, String> insertCategory(String name) throws SQLException 
	{
		String sql = "insert into inv_product_categories (name) values (?)";
		PreparedStatement insertCategoryStatement = this.connection.prepareStatement(sql);
		insertCategoryStatement.setString(1, name);
		insertCategoryStatement.execute();
		if (insertCategoryStatement.getUpdateCount() != 0) 
		{
			return getCategory(name);
		}
		insertCategoryStatement.clearParameters();
		return new HashMap<UUID, String>();
	}
	
	public Map<UUID, String> getCategory(String categoryName) throws SQLException {
		String sql = "select id, name from inv_product_categories where name = ?";
		PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
		preparedStatement.setString(1, categoryName);
		ResultSet result = preparedStatement.executeQuery();
		
		Map<UUID, String> category = new HashMap<UUID, String>();
		while(result.next()) { 
			category.put((UUID)result.getObject("id"), result.getString("name"));
		}
		return category;
	}
	
	private void getCategories() {
		
	}
}
