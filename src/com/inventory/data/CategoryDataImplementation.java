package com.inventory.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.inventory.model.Category;

public class CategoryDataImplementation {
	private Connection connection;
	
	public CategoryDataImplementation(Connection connection) {
		this.connection = connection;
	}
	
	public Category getCategoryById(UUID categoryId) throws SQLException {
		String sql = "select id, name from inv_product_categories where id = ?";
		PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
		preparedStatement.setObject(1, categoryId, java.sql.Types.OTHER);
		ResultSet result = preparedStatement.executeQuery();
		preparedStatement.clearParameters();
		
		Category category = new Category();
		while(result.next()) {
			category.setName(result.getString("name"));
			category.setId((UUID)result.getObject("id")); 
		}
		return category;
	}
	
	public Category getCategoryByName(String categoryName) throws SQLException {
		String sql = "select id, name from inv_product_categories where name = ?";
		PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
		preparedStatement.setString(1, categoryName);
		ResultSet result = preparedStatement.executeQuery();
		preparedStatement.clearParameters();
		
		Category category = new Category();
		while(result.next()) {
			category.setName(result.getString("name"));
			category.setId((UUID)result.getObject("id")); 
		}
		return category;
	}
	
	public boolean insertCategory(Category category) throws SQLException {
		String sql = "insert into inv_product_categories (name) values (?)";
		PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
		preparedStatement.setString(1, category.getName());
		preparedStatement.execute();
		preparedStatement.clearParameters(); 
		
		return preparedStatement.getUpdateCount() == 1;
	}
}
