package com.inventory.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.inventory.model.Category;
import static com.inventory.data.ProductCategorySql.*;

enum CategoryTable 
{
	CategoryTable();
	static final String ID = "id";
	static final String NAME = "name";
	static final String CREATED_ON = "created_on";
}

public class CategoryDataImplementation {
	private Connection connection;
	
	public CategoryDataImplementation(Connection connection) {
		this.connection = connection;
	}
	
	public Category getCategoryById(UUID categoryId) throws SQLException { 
		PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_PRODUCT_CATEGORY_BY_ID);
		preparedStatement.setObject(1, categoryId, java.sql.Types.OTHER);
		ResultSet result = preparedStatement.executeQuery();
		preparedStatement.clearParameters();
		
		Category category = new Category();
		while(result.next()) {
			category.setName(result.getString(CategoryTable.NAME));
			category.setId((UUID)result.getObject(CategoryTable.ID)); 
		}
		return category;
	}
	
	public Category getCategoryByName(String categoryName) throws SQLException {
		PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_PRODUCT_CATEGORY_BY_NAME);
		preparedStatement.setString(1, categoryName);
		ResultSet result = preparedStatement.executeQuery();
		preparedStatement.clearParameters();
		
		Category category = new Category();
		while(result.next()) {
			category.setName(result.getString(CategoryTable.NAME));
			category.setId((UUID)result.getObject(CategoryTable.ID)); 
		}
		return category;
	}
	
	public boolean insertCategory(Category category) throws SQLException {
		PreparedStatement preparedStatement = this.connection.prepareStatement(INSERT_PRODUCT_CATEGORY);
		preparedStatement.setString(1, category.getName());
		preparedStatement.execute();
		preparedStatement.clearParameters(); 
		
		return preparedStatement.getUpdateCount() == 1;
	}
}
