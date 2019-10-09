package com.inventory.util;

import javax.servlet.*;

import java.sql.*;

public class InventoryServletContextListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			ServletContext context = event.getServletContext();
			String url = context.getInitParameter("dbUrl");
			String user = context.getInitParameter("dbUser");
			String password = context.getInitParameter("dbPassword"); 
					
			Connection connection = DriverManager.getConnection(url, user, password);
			
			if (connection != null) {
				context.setAttribute("dbConnection", connection);
				System.out.println("db connection successfull");
			} else {
				System.out.println("db connection not successfull");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) { 
	}
}
