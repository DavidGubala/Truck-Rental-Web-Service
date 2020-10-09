package com.truck.dal;

import java.util.ArrayList;

import com.truck.product.Product;

public class InventoryDAO {
	/*
	 * Under Construction
	 * */
	// Create
	public void createInventory() {
		
	}
	
	// Read
	public Product[] getInventory(int invId) {
		Product[] inventory = null; // filler
		return inventory;
	}
	
	/*Update
	 * I'm thinking two in this case. Really the only updates to a partner's inventory
	 * will be the addition and deletiong of productid's from the inventory data table
	 * */
	public void addToInventory(int productId) {
		
	}
	
	public void deleteFromInventory(int productId) {
		
	}
	
	// Delete
	public void deleteInventory(int invId) {
		
	}

}
