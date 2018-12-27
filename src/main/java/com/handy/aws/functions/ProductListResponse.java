package com.handy.aws.functions;

import java.util.List;
import com.handy.aws.domain.Product;

public class ProductListResponse {

	private List<Product> body;

	public ProductListResponse() {
		super();
	}
	public ProductListResponse(List<Product> body) {
		super();
		this.body = body;
	}
	public List<Product> getBody() {
		return body;
	}
	public void setBody(List<Product> body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "ProductResponse [" + (body != null ? "body=" + body : "") + "]";
	}
}
