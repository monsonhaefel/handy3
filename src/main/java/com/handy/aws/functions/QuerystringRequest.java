package com.handy.aws.functions;

import java.util.Map;

public class QuerystringRequest {
    
    Map<String, String> queryStringParameters;
    
    public Map<String, String> getQueryStringParameters() {
        return queryStringParameters;
    }
    public void setQueryStringParameters(Map<String, String> queryStringsMap) {
        queryStringParameters = queryStringsMap;
    }
    
}