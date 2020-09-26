package com.truck.model.service;

import com.truck.dal.ProductDAO;
import com.truck.model.product.Product;


public class ProductManager {
	private ProductDAO prodDAO = new ProductDAO();
	
	public Product findProductById(int productId) {
		
		try {
			Product product = prodDAO.getProduct(productId);
			return product;
		} catch (Exception se) {
			System.err.println("ProductManager: Threw a Exception retrieving product.");
			System.err.println(se.getMessage());
		}
		return null;
	}
	
	public void addProduct(Product product) {
		
		try {
			prodDAO.addProduct(product);
		} catch (Exception se) {
			System.err.println("ProductManager: Threw a Exception retrieving product.");
			System.err.println(se.getMessage());
		}
	}
}
