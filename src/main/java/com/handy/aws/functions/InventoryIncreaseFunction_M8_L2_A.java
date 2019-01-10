package com.handy.aws.functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.handy.aws.domain.Product;

public class InventoryIncreaseFunction_M8_L2_A implements RequestHandler<InventoryIncreaseRequest, InventoryUpdateResponse> {

	
    @Override
    public InventoryUpdateResponse handleRequest(InventoryIncreaseRequest request, Context context) {
    	
    	List<Product> products = request.getBody();
        
        boolean success = increaseInventory(products, context);
        
        if(success) {
        	return new InventoryUpdateResponse(400,products);
        }else {
        	return new InventoryUpdateResponse();
        }
    }
	
    public boolean increaseInventory(List<Product> products, Context context) {
    	
    	  
    	Map<String, String> env = System.getenv();
    	final String RDS_INSTANCE_PORT = env.get("RDS_INSTANCE_PORT");
    	final String RDS_DB_NAME = env.get("RDS_DB_NAME");
    	final String RDS_INSTANCE_HOSTNAME = env.get("RDS_INSTANCE_HOSTNAME");
    	
        final String JDBC_URL = "jdbc:mysql://" + RDS_INSTANCE_HOSTNAME + ":" + RDS_INSTANCE_PORT + "/"+RDS_DB_NAME;
        

        try {
            
        	// NOTE: getDBProperties() MAY NOT BE NEEDED. TEST WITHOUT.
            Connection con = DriverManager.getConnection(JDBC_URL, getDBProperties());
            
            con.setAutoCommit(false);
            PreparedStatement preparedStatement = 
            		con.prepareStatement("UPDATE HandyDev.inventory SET count = count + ? WHERE product_id = ?");
            try { 
            	for (Product product : products) {
            		preparedStatement.setInt(1, product.getCount());
            		preparedStatement.setInt(2, product.getProduct_id());
            		preparedStatement.execute();
            	};

            } catch (SQLException e) {
            	context.getLogger().log("JDBC exception: " + e.toString());
            	con.rollback();
            	return false;
            }
            con.commit();
            
        }catch(SQLException e) {
            context.getLogger().log("JDBC exception: " + e.toString());
            return false;
        }
        return true;
    }
    
    private Properties getDBProperties() {
    	
    	Map<String, String> env = System.getenv();
    	String DB_USER = env.get("DB_USER");
    	final String DB_PASS = env.get("DB_PASS");

    	java.util.Properties props = new java.util.Properties();
        props.put("user",DB_USER);
        props.put("password", DB_PASS);
        
        return props;
    }

}
