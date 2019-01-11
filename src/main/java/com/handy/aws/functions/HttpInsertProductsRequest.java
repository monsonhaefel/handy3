package com.handy.aws.functions;

import java.util.List;

import com.handy.aws.domain.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class HttpInsertProductsRequest {
	
	String body;
	Gson gson = new Gson();
	
	public HttpInsertProductsRequest() {
		super();
	}

	public HttpInsertProductsRequest(String body) {
		this();
		this.body = body;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public List<Product> getProducts(){
		return gson.fromJson(body, new TypeToken<List<Product>>() {}.getType());
	}

	@Override
	public String toString() {
		return "HttpInsertProductsRequest [" + (body != null ? "body=" + body : "") + "]";
	}

}
