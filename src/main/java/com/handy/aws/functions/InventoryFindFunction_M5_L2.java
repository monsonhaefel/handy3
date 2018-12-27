package com.handy.aws.functions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.handy.aws.domain.Product;



public class InventoryFindFunction_M5_L2 implements RequestHandler<QuerystringRequest, StringResponse>{  
    
	private Product [] products;
    
    @Override
    public StringResponse handleRequest(QuerystringRequest request, Context context){
        
        
        String ids = (String)request.queryStringParameters.getOrDefault("id", "-1");
        
        Integer idi = Integer.parseInt(ids);
        
        Product product = getProductById(idi, context);
        
        if(product != null){
            
        	StringResponse response = new StringResponse();
            response.setBody("Product Selected is: " + product.toString());
            return response;
            
        }else {
        
        	StringResponse response = new StringResponse();
            response.setBody("Error. Id submitted was : " + idi);
            return response;
        }
        
    }
    
    private Product getProductById(Integer productId, Context context) {
    	
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
