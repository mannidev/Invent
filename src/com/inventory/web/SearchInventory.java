package com.inventory.web;

import com.inventory.model.*;

import javax.servlet.*;
import javax.servlet.http.*;
 
import java.io.*;
import java.util.List;

public class SearchInventory extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 
		response.setContentType("text/html");
		
		String productCategory = request.getParameter("productCategory");
		
		if (productCategory == null) {
			productCategory = getServletConfig().getInitParameter("defaultSearchParam");
		}
		
		ServletContext context = getServletContext();
		ProductAdvisor prodAdvisor = new ProductAdvisor(context);
		List<String> products = prodAdvisor.getProducts(productCategory);
		
		request.setAttribute("recommendedProducts", products);
		RequestDispatcher view = request.getRequestDispatcher("result.jsp");
		view.forward(request, response);
	}
}
