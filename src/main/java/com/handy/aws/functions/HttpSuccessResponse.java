package com.handy.aws.functions;

import java.util.Map;
import java.util.HashMap;

public class HttpSuccessResponse {
    
    String statusCode = "200";
    Map<String, String> headers = new HashMap<String, String>();
    String body;
   
    
    public HttpSuccessResponse() {
        this.headers.put("Content-Type","application/json");
    }
    public HttpSuccessResponse(boolean success) {
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
    	this.body = success ? "SUCCESS" : "FAILED";
    }
    public Boolean getSuccess() {
    	
    	if(this.body == null) { return false;}
    	return this.body.equalsIgnoreCase("SUCCESS") ? true : false;
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