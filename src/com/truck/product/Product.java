package com.truck.product;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product {
	private int productId;
	private String productType;
	private double pricePerMile;
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public String getProductType() {
		return productType;
	}
	
	public void setProductType(String prodType) {
		this.productType = prodType;
	}
	
	public double getPricePerMile() {
		return pricePerMile;
	}
	
	public void setPricePerMile(double pricePerMile) {
		this.pricePerMile = pricePerMile;
	}
}
