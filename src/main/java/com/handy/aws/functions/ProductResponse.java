package com.handy.aws.functions;


import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.handy.aws.domain.Product;


public class ProductResponse {
	
	private String body;
    
	String statusCode = "200";
    Map<String, String> headers = new HashMap<String, String>();
    
    // Using the Gson class and instruct a Gson instance
    Gson gson = new Gson(); 
    
    public ProductResponse() {
    	super();
        this.headers.put("Content-Type","application/json");
    }
    public ProductResponse(String errorMsg) {
    	this();
    	this.body = errorMsg;
    }
    public ProductResponse(Product product) {
        this();
        // Serialize the Product object to JSON
        this.body = gson.toJson(product);
        
    }
	public String getBody() {
		return body;
	}
	// this is needed if the return is to be an error. 
	public void setBody(String errorMsg) {
		this.body = errorMsg;
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
