package com.handy.aws.functions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;
import com.handy.aws.domain.Product;

public class InventoryDecreaseFunction_M8_L2 extends JdbcUpdateInventory implements RequestHandler<InventoryDecreaseRequest, InventoryUpdateResponse> {
    
	@Override
    public InventoryUpdateResponse handleRequest(InventoryDecreaseRequest request, Context context){
        
        
    	List<Product> products = request.getBody();
        
        boolean success = updateInventory(Action.DECREASE, products, context);
        
        if(success) {
        	return new InventoryUpdateResponse(400,products);
        }else {
        	return new InventoryUpdateResponse();
        }

	}
}
  
