package com.inventory.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inventory.model.Category;
import com.inventory.model.InventoryItem;
import com.inventory.service.InventoryService;

public class NewInventory extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String name = request.getParameter("productName");
		int quantity = Integer.parseInt(request.getParameter("productQuantity"));
		String categoryName = request.getParameter("productCategory"); 
		InventoryItem inventoryItem = new InventoryItem(name, quantity, categoryName); 
		
		InventoryService inventoryService = new InventoryService(getServletContext());
		boolean result = inventoryService.CreateNewInventoryItem(inventoryItem); 
	}

}
