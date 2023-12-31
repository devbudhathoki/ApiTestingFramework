package com.apitesting.apirequests;

import com.apitesting.annotations.FrameworkAnnotation;
import com.apitesting.reports.ExtentLogger;
import com.apitesting.requestbuilder.RequestBuilder;

import io.restassured.response.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import static org.assertj.core.api.Assertions.*;


public class GetRequestTests {

   private static final Logger logger = LogManager.getLogger();
    @Test
    @FrameworkAnnotation(author={"Robert", "Dev"}, category = {"Regression","Smoke"})
    public void getClientsDetail() {
        Response response = RequestBuilder.buildRequestForGetCalls().get("/clients");
        ExtentLogger.logResponse(response.asPrettyString());

        assertThat(response.getStatusCode()).isEqualTo(200);

        logger.info(response.jsonPath().getList("$").toString());
        assertThat(response.jsonPath().getList("$").size()).isPositive().as("Validating size of the client array").isGreaterThan(1);
    }

    @Test(dataProvider = "getData")
    @FrameworkAnnotation(author={ "Steve"}, category = {"Regression"})
    public void getClientDetail(Integer id, String name){
        Response response = RequestBuilder.buildRequestForGetCalls().pathParams("id", id).get("/clients/{id}");

        ExtentLogger.info("Parameters: " + id.toString() + " " + name);
        ExtentLogger.logResponse(response.asPrettyString());
        assertThat(response.getStatusCode()).as("Status code failed").isEqualTo(200);
        logger.info(response.getStatusCode() + " OK");

    }

    @DataProvider
    public Object[][] getData(){
        return  new Object[][]{{1,"Dev"}, {2, "David"}};
    }


}
