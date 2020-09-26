package com.truck.dal;
import com.truck.model.product.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.truck.model.product.*;
import com.truck.model.user.Partner;

public class ProductDAO {
	
	public Product getProduct(String productId) {
		return null;
	}
	
	public void addProduct(Product product) {
		return;
	}
	/*
	 * Under Construction
	 * */
	// Create
	public void addProduct(Product prod) {
		
	}
	// Read
	public Product getProduct(int probId) {
		Product placeholder = new Product();
		return placeholder;
	}
	// Update
	public void EditProduct(Product prod) {
		
	}	
	// Delete
	public void deletePartner(int probId) {
		
	}
}
