package com.handy.aws.functions;

import java.util.Map;
import java.util.HashMap;

public class HttpHtmlResponse {
    
    String statusCode = "200";
    Map<String, String> headers = new HashMap<String, String>();
    String body;
    
    public HttpHtmlResponse() {
        this.headers.put("Content-Type","text/html");
    }
    public HttpHtmlResponse(String bodyText) {
        this();
        this.body = bodyText;
        
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
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

}