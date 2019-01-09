package com.handy.aws.functions;

import com.google.gson.Gson;

import java.util.List;
import com.handy.aws.domain.Product;

public class ProductListResponse {

	private String body;
	private List<Product> products;
	
    // Using the Gson class and instruct a Gson instance
    Gson gson = new Gson(); 

	public ProductListResponse() {
		super();
	}
	public ProductListResponse(List<Product> products) {
		super();
		this.products = products;
	}
	public String getBody() {
		if(products != null) {
			return gson.toJson(products);
		}else {
			return body;
		}
	}
	public void setBody(List<Product> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "ProductListResponse [" + (body != null ? "body=" + body + ", " : "")
				+ (products != null ? "products=" + products : "") + "]";
	}

}
