package com.handy.aws.functions;

import com.handy.aws.domain.Product;

public class ProductResponse {
	
	private Product body;

	public ProductResponse() {
		super();
	}
	public ProductResponse(Product body) {
		super();
		this.body = body;
	}
	public Product getBody() {
		return body;
	}
	public void setBody(Product body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "ProductResponse [" + (body != null ? "body=" + body : "") + "]";
	}
	

}
