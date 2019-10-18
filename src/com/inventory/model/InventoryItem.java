package com.inventory.model;

import java.util.UUID;

public class InventoryItem 
{
	private UUID id;
	private String name;
	private int quantity;
	private Category category;
	
	public InventoryItem() { 
	}
	
	public InventoryItem(String name, int quantity, String categoryName) 
	{
		this.name = name;
		this.quantity = quantity;
		this.category =  new Category(categoryName); 
	}
	
	public UUID getId() 
	{
		return id;
	} 
	
	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
	
	public Category getCategory() 
	{
		return category;
	}
	
	public void setCategory(Category category) 
	{
		this.category = category;
	} 
}
