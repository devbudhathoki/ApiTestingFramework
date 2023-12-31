package com.apitesting.requestbuilder;

import com.apitesting.factory.APIConfigFactory;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestBuilder  {

    private RequestBuilder(){}

    private static final String BASE_URL = APIConfigFactory.getConfig().baseurl();

    public static RequestSpecification buildRequestForGetCalls(){
        return given()
                .baseUri(BASE_URL)
                .log()
                .all();
    }

    public static RequestSpecification buildRequestForPostCalls(){
        return buildRequestForGetCalls()
                .contentType(ContentType.JSON);
    }
}
