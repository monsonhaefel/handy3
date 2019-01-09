package com.handy.aws.functions;


import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.handy.aws.domain.Product;


public class ProductResponse {
	
	//Change the type to String. The response should be returned to the API Gateway as a String.
	private String body;
    
	String statusCode = "200";
    Map<String, String> headers = new HashMap<String, String>();
    
    // Using the Gson class and instruct a Gson instance
    Gson gson = new Gson(); 
    
    public ProductResponse() {
        this.headers.put("Content-Type","application/json");
    }
    public ProductResponse(Product product) {
        this();
        // Serialize the Product object to JSON
        this.body = gson.toJson(product);
        
    }
	public String getBody() {
		return body;
	}
	public void setBody(Product product) {
		// Serialize the Product object to JSON
		this.body = gson.toJson(product);
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public Map<String, String> getHeaders() {
		return headers;
	}
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

}
