package com.handy.aws.functions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.handy.aws.domain.Product;

public class InventoryFindFunction_M4_L4 implements RequestHandler<Object, String> {
 	
 	private Product [] products;
    
 	@Override
    public String handleRequest(Object input, Context context) {
        return getProductById(0).toString();
    }
    
    private Product getProductById(Integer productId) {
    	
    	Integer index = productId - 100;
    	
    	if(index < 0 && index > 2) { return null;}
    	
    	if(products == null) {
    		products = new Product[3];
    		products[0] = new Product(100, "Hammer", "Stanley", "5oz Magnetic Tack Hammer",    20);
    		products[1] = new Product(101, "Hammer", "Wilton Bash", "24oz Ball Peen",          27);
    		products[2] = new Product(102, "Hammer", "DeWalt", "15oz MIG Weld",                17);
    	}
    	
    	
    	return products[productId];
    }
}