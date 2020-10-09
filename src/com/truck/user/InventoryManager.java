package com.truck.user;

import com.truck.dal.*;
import com.truck.product.Product;

public class InventoryManager {
	
	private InventoryDAO invDAO = new InventoryDAO();
	
	// Create
	public void createInventory() {
		try {
			invDAO.createInventory();
	    } catch (Exception se) {
	      System.err.println("InventoryManager: Threw a Exception creating inventory.");
	      System.err.println(se.getMessage());
	    }
	}
	
	// Read
	public Product[] getInventory(int invId) {
		try {
			Product[] inventory = invDAO.getInventory(invId);
			return inventory;
	    } catch (Exception se) {
	      System.err.println("InventoryManager: Threw a Exception retreiving inventory.");
	      System.err.println(se.getMessage());
	    }
		return null;
	}
	
	//Update
	public void addToInventory(int productId) {
		try {
			invDAO.addToInventory(productId);;
	    } catch (Exception se) {
	      System.err.println("InventoryManager: Threw a Exception adding procusts to inventory.");
	      System.err.println(se.getMessage());
	    }
	}
	
	public void deleteFromInventory(int productId) {
		try {
			invDAO.deleteFromInventory(productId);;
	    } catch (Exception se) {
	      System.err.println("InventoryManager: Threw a Exception deleting products from inventory.");
	      System.err.println(se.getMessage());
	    }
	}
	
	// Delete
	public void deleteInventory(int invId) {
		try {
			invDAO.deleteInventory(invId);;
	    } catch (Exception se) {
	      System.err.println("InventoryManager: Threw a Exception deleting inventory.");
	      System.err.println(se.getMessage());
	    }
	}
}
