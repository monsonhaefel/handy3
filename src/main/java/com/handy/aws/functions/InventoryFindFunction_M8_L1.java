package com.handy.aws.functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.handy.aws.domain.Product;

public class InventoryFindFunction_M8_L1 implements RequestHandler<QuerystringRequest, ProductListResponse>{  

	@Override
	public ProductListResponse handleRequest(QuerystringRequest request, Context context){

		String ids = (String)request.queryStringParameters.getOrDefault("id", "-1");

		ProductListResponse response = new ProductListResponse();

		if(ids.equalsIgnoreCase("All")) {

			List<Product> products = getAllProducts(context);
			if(products != null) {
				response.setBody(products);
			}
			
		}else {
			Integer idi = Integer.parseInt(ids);

			Product product = getProductById(idi, context);

			List<Product> products = new ArrayList<Product>();
			products.add(product);
			
			if(product != null){
				response.setBody(products);
			}
		}
		return response;


	}
    public Product getProductById(Integer productId, Context context) {
    	return this.getProducts(productId, context).get(0);
    }
    public List<Product> getAllProducts(Context context) {
    	
    	return this.getProducts(null,context);
    	
    }
    private List<Product> getProducts(Integer productId, Context context) {
    	
    	Map<String, String> env = System.getenv();
    	final String RDS_INSTANCE_PORT = env.get("RDS_INSTANCE_PORT");
    	final String RDS_DB_NAME = env.get("RDS_DB_NAME");
    	final String RDS_INSTANCE_HOSTNAME = env.get("RDS_INSTANCE_HOSTNAME");
    	
        final String JDBC_URL = "jdbc:mysql://" + RDS_INSTANCE_HOSTNAME + ":" + RDS_INSTANCE_PORT + "/"+RDS_DB_NAME;
        
    	
    	List<Product> products = new ArrayList<Product>();
    	
        try {
            
            Connection con = DriverManager.getConnection(JDBC_URL, getDBProperties());
            Statement stmt = con.createStatement();
            
            ResultSet rs = null;
            if(productId == null) { // selecting all
            	rs = stmt.executeQuery("SELECT * FROM HandyDev.inventory");
            }else {
            	rs = stmt.executeQuery("SELECT * FROM HandyDev.inventory WHERE product_id = " + productId);
            }
        
            
            while (rs.next()) {
            	  String prodIdstr = rs.getString("product_id");
                  String toolType = rs.getString("tool_type");
                  String brand = rs.getString("brand");
                  String name = rs.getString("name");
                  String countStr = rs.getString("count");
                  int count = Integer.parseInt(countStr);
                  int prodId = Integer.parseInt(prodIdstr);
                  Product product = new Product(prodId, toolType, brand, name, count);
                  products.add(product);
            }
            
        }catch( Exception e) {
            context.getLogger().log("JDBC exception: " + e.toString());
            return null;
        }
        context.getLogger().log("return  null");
        return products;
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
