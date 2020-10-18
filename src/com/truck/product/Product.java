package com.truck.product;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product {
	private int productId;
	private String productType;
	private int pricePerMile;
	
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
	
	public int getPricePerMile() {
		return pricePerMile;
	}
	
	public void setPricePerMile(int pricePerMile) {
		this.pricePerMile = pricePerMile;
	}
}
