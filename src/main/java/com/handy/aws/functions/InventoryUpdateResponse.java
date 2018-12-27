package com.handy.aws.functions;

import java.util.List;
import com.handy.aws.domain.Product;

public class InventoryUpdateResponse {
	
	int statusCode = 200;
 	List<Product> body;
 	
	public InventoryUpdateResponse() {
		super();
	}
	public InventoryUpdateResponse(int statusCode, List<Product> body) {
		super();
		this.statusCode = statusCode;
		this.body = body;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public List<Product> getBody() {
		return body;
	}
	public void setBody(List<Product> body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "InventoryUpdateResponse [statusCode=" + statusCode + ", " + (body != null ? "body=" + body : "") + "]";
	}


}
