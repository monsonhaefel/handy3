package com.handy.aws.functions;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class HttpSuccessGSONResponse {
	
    enum success {
    	TRUE, FALSE;
    }
    String statusCode = "200";
    Map<String, String> headers = new HashMap<String, String>();
    String body;
   
    // Using the Gson class and instruct a Gson instance
    Gson gson = new Gson(); 
    
    public HttpSuccessGSONResponse() {
        this.headers.put("Content-Type","application/json");
    }
    public HttpSuccessGSONResponse(boolean success) {
        this();
        this.setSuccess(success);
        
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public void setSuccess(Boolean success) {
    	this.body = success ? gson.toJson(HttpSuccessGSONResponse.success.TRUE) : gson.toJson(HttpSuccessGSONResponse.success.FALSE);
    }
    public Boolean getSuccess() {
    	
    	if(this.body == null) { return false;}
    	return this.body.equalsIgnoreCase("TRUE") ? true : false;
    }
    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    public Map<String, String> getHeaders() {
        return headers;
    }
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }


}
