package com.truck.domain.model.product;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product {
	private int productId;
	private String productType;
	private int price;
	
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
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int Price) {
		this.price = Price;
	}
}
