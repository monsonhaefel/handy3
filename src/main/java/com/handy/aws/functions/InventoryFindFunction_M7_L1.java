package com.handy.aws.functions;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.handy.aws.domain.Product;
public class InventoryFindFunction_M7_L1 implements RequestHandler<QuerystringRequest, ProductResponse>{  
    
    @Override
    public ProductResponse handleRequest(QuerystringRequest request, Context context){
        
        
        String ids = (String)request.queryStringParameters.getOrDefault("id", "-1");
        
        Integer idi = Integer.parseInt(ids);
        
        Product product = getProductById(idi, context);
       
        ProductResponse response = new ProductResponse();
        
        if(product != null){
            response.setBody(product);
        }
        return response;
        
        
    }
    private Product getProductById(Integer productId, Context context) {
    	
    	final String RDS_INSTANCE_PORT = "3306";
        final String RDS_DB_NAME = "HandyDev";
        final String RDS_INSTANCE_HOSTNAME = "handydev.cabbkm3wjh6o.us-east-1.rds.amazonaws.com";
        
        final String JDBC_URL = "jdbc:mysql://" + RDS_INSTANCE_HOSTNAME + ":" + RDS_INSTANCE_PORT + "/"+RDS_DB_NAME;
        
        Product product = null;
        
        try {
            
            Connection con = DriverManager.getConnection(JDBC_URL, getDBProperties());
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM HandyDev.inventory WHERE product_id = " + productId);
            while (rs.next()) {
                  String toolType = rs.getString("tool_type");
                  String brand = rs.getString("brand");
                  String name = rs.getString("name");
                  String countStr = rs.getString("count");
                  int count = Integer.parseInt(countStr);
                  Product prod = new Product(productId, toolType, brand, name, count);
                  product = prod;
            }
            
        }catch( Exception e) {
            context.getLogger().log("JDBC exception: " + e.toString());
        }
        return product;
 
    }
    
    private Properties getDBProperties() {
    	
        final String DB_USER = "masteruser";
        final String DB_PASS = "Darwin1859";
         
    	java.util.Properties props = new java.util.Properties();
        props.put("user",DB_USER);
        props.put("password", DB_PASS);

        return props;
    }
    
}