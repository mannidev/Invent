package com.inventory.web;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inventory.model.InventoryItem;
import com.inventory.service.InventoryService;
import com.inventory.util.InventoryConstants;

public class Inventory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String name = request.getParameter(InventoryConstants.PRODUCT_NAME);
		int quantity = Integer.parseInt(request.getParameter(InventoryConstants.PRODUCT_QUANTITY));
		String categoryName = request.getParameter(InventoryConstants.PRODUCT_CATEGORY); 
		InventoryItem inventoryItem = new InventoryItem(name, quantity, categoryName); 

		InventoryService inventoryService = new InventoryService(getServletContext());
		UUID result = inventoryService.createNewInventoryItem(inventoryItem);
		
		if (result != null) 
		{
			inventoryItem = inventoryService.getInventoryById(result);
			request.setAttribute(InventoryConstants.INVENTORY_ITEM, inventoryItem);
			RequestDispatcher dispatcher = request.getRequestDispatcher("ViewInventory.jsp");
			dispatcher.forward(request, response);
		}
	}

}
