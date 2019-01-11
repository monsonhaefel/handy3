package com.handy.aws.functions;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.handy.aws.domain.Product;

public class HttpProductListResponse {

	private String body;
    
	String statusCode = "200";
    Map<String, String> headers = new HashMap<String, String>();
    
    // Using the Gson class and instruct a Gson instance
    Gson gson = new Gson(); 

	public HttpProductListResponse() {
		super();
        this.headers.put("Content-Type","application/json");
	}
	public HttpProductListResponse(String errorMsg) {
		this();
		this.body = errorMsg;
	}
	public HttpProductListResponse(List<Product> products) {
		this();
		this.body = gson.toJson(products);
	}
	public String getBody() {
		return body;
	}
	public void setBody(List<Product> products) {
		this.body = gson.toJson(products);
	}
	public void setBody(String errorMsg) {
		this.body = errorMsg;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

}
