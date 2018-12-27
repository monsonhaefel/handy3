package com.handy.aws.functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.amazonaws.services.lambda.runtime.Context;
import com.handy.aws.domain.Product;

public class JdbcUpdateInventory {
	
	public enum Action {
		INCREASE, DECREASE;
	}
	
    public boolean updateInventory(Action action, List<Product> products, Context context) {
    	
    	String updateSymbol;
		if(action == Action.INCREASE) {
			updateSymbol = "+";
		}else {
			updateSymbol = "-";
		}
    	  
    	Map<String, String> env = System.getenv();
    	String endpoint = env.get("RDS_INSTANCE_HOSTNAME");
    	String port = env.get("RDS_INSTANCE_PORT");
    	String dbName = env.get("DB_NAME");
        
        final String JDBC_URL = "jdbc:mysql://" + endpoint + ":" + port + "/"+dbName;

        try {
            
        	// NOTE: getDBProperties() MAY NOT BE NEEDED. TEST WITHOUT.
            Connection con = DriverManager.getConnection(JDBC_URL, getDBProperties());
            
            con.setAutoCommit(false);
            PreparedStatement preparedStatement = 
            		con.prepareStatement("UPDATE HandyDev.inventory SET count = count ? ? WHERE product_id = ?");
            try { 
            	for (Product product : products) {
            		preparedStatement.setString(1, updateSymbol);
            		preparedStatement.setInt(2, product.getCount());
            		preparedStatement.setInt(3, product.getProduct_id());
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
    
    // IM NOT SURE THIS IS EVEN NEEDED. TEST WITHOUT PROPERTIES TO SEE.
    private Properties getDBProperties() {
    	

        final String REGION_NAME = "us-east-1";
        final String DB_USER = "masteruser";
        final String DB_PASS = "12345678";
        final String DB_NAME = "handydev";
         
    	java.util.Properties props = new java.util.Properties();
        props.put("user",DB_USER);
        props.put("password", DB_PASS);
        props.put("db_name", DB_NAME);
        props.put("name", DB_NAME);
        props.put("region_name", REGION_NAME);
        return props;
    }

}
