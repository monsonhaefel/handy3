package com.handy.aws.functions;

import com.handy.aws.functions.HttpInsertProductsRequest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class TestPostFormatFunction implements RequestHandler<HttpInsertProductsRequest, String> {

    @Override
    public String handleRequest(HttpInsertProductsRequest input, Context context) {
        context.getLogger().log("Input: " + input);

        // TODO: implement your handler
        return "Hello from Lambda!";
    }

}
