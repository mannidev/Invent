package com.inventory.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

public class ProductAdvisor {
	private ServletContext context;
	public ProductAdvisor(ServletContext context) {
		this.context = context;
	}
	
	public List<String> getProducts(String productCategory) {
		List<String> products = new ArrayList<String>();
		try {
			if (productCategory != null) {
				switch (productCategory.strip().toLowerCase()) {
				case "fruit":
					products.add("Apple");
					products.add("Banana");
					break;
				case "grocery":
					products.add("Enfamil");
					products.add("Kraft Dinner");
					break;
				case "sports":
					products.add("Fitness Tracker");
					products.add("Basketball");
					break;
				case "diys":
					Connection connection = (Connection) this.context.getAttribute("dbConnection");
					if (connection != null) {
						PreparedStatement sqlStatement = connection.prepareStatement("select * from inv_products");
						ResultSet resultSet = sqlStatement.executeQuery();
						while(resultSet.next()) {
							products.add(resultSet.getString("name"));
						}
					}else {
						products.add(this.context.getInitParameter("dbUrl"));
						products.add(this.context.getInitParameter("dbUser"));
						products.add(this.context.getInitParameter("dbPassword"));
					}
					
					break;
				default:
					products.add("No products with category " + productCategory);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return products;
	}
}
