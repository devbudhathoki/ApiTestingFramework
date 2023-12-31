package com.apitesting.apirequests;

import com.apitesting.annotations.FrameworkAnnotation;
import com.apitesting.constants.FrameworkConstants;
import com.apitesting.reports.ExtentLogger;
import com.apitesting.reports.ExtentReport;
import com.apitesting.utils.ApiUtils;
import com.apitesting.utils.PropertyUtils;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Objects;

import static io.restassured.RestAssured.given;

public class AuthTests {

    //Basic auth
    //Bearer Token
    //OAuth2.0

    @Test
    @FrameworkAnnotation
    public void basicAuthTestUsernamePassword() {
        Response response = given()
                .auth()
                .basic("postman", "password")
                .log()
                .all()
                .get("https://postman-echo.com/basic-auth");

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @Test
    @FrameworkAnnotation
    public void basicAuthBase64Encode(){
        Response response = given()
                .config(RestAssured.config().logConfig(LogConfig.logConfig().blacklistHeader("Authorization")))
                .header("Authorization", "Basic cG9zdG1hbjpwYXNzd29yZA==")
                .log()
                .all()
                .get("https://postman-echo.com/basic-auth");

        ExtentLogger.logResponse(response.prettyPrint());

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);

        Assertions.assertThat(response.jsonPath().getMap("$").get("authenticated")).isEqualTo(true);
    }

    @Test
    @FrameworkAnnotation
    public void postOpenAI(){
        Response post = given()
                .header("Authorization", "Bearer " + PropertyUtils.getValue("openAIKey"))
                .config(RestAssured.config().logConfig(LogConfig.logConfig().blacklistHeader("Authorization","Content-Type")))
                .header("Content-Type", "application/json")
                .body(ApiUtils.readJsonAndGetAsString(FrameworkConstants.getRequestFolder() + "openAIRequests.json"))
                .log()
                .all()
                .post("https://api.openai.com/v1/chat/completions");
       ExtentLogger.logResponse(post.prettyPrint());
       post.jsonPath().getMap("$").entrySet().forEach(e -> System.out.println(e.getKey() + " = " + e.getValue()));


    }


}
