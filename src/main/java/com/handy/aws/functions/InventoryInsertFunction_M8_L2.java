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

public class InventoryInsertFunction_M8_L2 implements RequestHandler<HttpInsertProductsRequest, HttpSuccessResponse> {
 	
    @Override
    public HttpSuccessResponse handleRequest(HttpInsertProductsRequest request, Context context) {
    	
    	List<Product> products = request.getProducts();
    	
    	boolean success = insertInventory(products, context);
        
    	if(!success) {
        	HttpSuccessResponse response = new HttpSuccessResponse(false);
        	response.setStatusCode("400");
        	return response;
        }else {
        	return new HttpSuccessResponse(true);
        }
    }
 	
    public boolean insertInventory(List<Product> products, Context context) {
    	
    	Map<String, String> env = System.getenv();
    	final String RDS_INSTANCE_PORT = env.get("RDS_INSTANCE_PORT");
    	final String RDS_DB_NAME = env.get("RDS_DB_NAME");
    	final String RDS_INSTANCE_HOSTNAME = env.get("RDS_INSTANCE_HOSTNAME");
    	
        final String JDBC_URL = "jdbc:mysql://" + RDS_INSTANCE_HOSTNAME + ":" + RDS_INSTANCE_PORT + "/"+RDS_DB_NAME;
        
    	try {
            
            Connection con = DriverManager.getConnection(JDBC_URL, getDBProperties());
            
        	con.setAutoCommit(false);
            PreparedStatement preparedStatement = 
            		con.prepareStatement(
            				"INSERT INTO HandyDev.inventory (tool_type, brand, name, count) \n" + 
            				"VALUES (?, ?, ?, ?);");
       	
            try { 
            	for (Product product : products) {
            		preparedStatement.setString(1, product.getToolType());
            		preparedStatement.setString(2, product.getBrand());
            		preparedStatement.setString(3, product.getName());
            		preparedStatement.setInt(4, product.getCount());
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
