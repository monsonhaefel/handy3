package com.handy.aws.domain;

public class Product {
	
	int product_id;
	String toolType;
	String brand;
	String name;
	int count;
	
	public Product() {
		super();
	}

	public Product(int product_id, String toolType, String brand, String name, int count) {
		super();
		this.product_id = product_id;
		this.toolType = toolType;
		this.brand = brand;
		this.name = name;
		this.count = count;
	}
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getToolType() {
		return toolType;
	}
	public void setToolType(String toolType) {
		this.toolType = toolType;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", " + (toolType != null ? "toolType=" + toolType + ", " : "")
				+ (brand != null ? "brand=" + brand + ", " : "") + (name != null ? "name=" + name + ", " : "")
				+ "count=" + count + "]";
	}
	
	

}
