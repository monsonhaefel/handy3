package com.handy.aws.functions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;
import com.handy.aws.domain.Product;

public class InventoryIncreaseFunction_M8_L2_B extends JdbcUpdateInventory implements RequestHandler<InventoryIncreaseRequest, InventoryUpdateResponse>{  
    
    @Override
    public InventoryUpdateResponse handleRequest(InventoryIncreaseRequest request, Context context){
        
        
    	List<Product> products = request.getBody();
        
        boolean success = updateInventory(Action.INCREASE, products, context);
        
        if(success) {
        	return new InventoryUpdateResponse(400,products);
        }else {
        	return new InventoryUpdateResponse();
        }

        
    }
}