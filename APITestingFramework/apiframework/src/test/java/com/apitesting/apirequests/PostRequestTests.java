package com.apitesting.apirequests;

import com.apitesting.annotations.FrameworkAnnotation;
import com.apitesting.constants.FrameworkConstantSingleton;
import com.apitesting.entity.Client;
import com.apitesting.reports.ExtentLogger;
import com.apitesting.requestbuilder.RequestBuilder;
import com.apitesting.utils.ApiUtils;
import com.apitesting.utils.RandomUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PostRequestTests {

    //with a pojo Client class

    @Test
    @FrameworkAnnotation
    public void postTestUsingPojo() {
        Client client = Client.builder()
                .setId(RandomUtils.getId())
                .setAge(RandomUtils.getAge())
                .setName(RandomUtils.getName())
                .setAddress(RandomUtils.getAddress()).build();

        System.out.println(client);
        RequestSpecification requestSpecification = RequestBuilder.buildRequestForPostCalls().body(client);


        Response response = requestSpecification.post("/clients");
        System.out.println(response.prettyPrint());
        assertThat(response.getStatusCode()).as("post status code").isEqualTo(201);

        ExtentLogger.logResponse(response.prettyPrint());
        ;


    }

    @Test
    @FrameworkAnnotation
    public void postRequestUsingJsonFile(Method method) throws IOException {
        String jsonString = ApiUtils.readJsonAndGetAsString(FrameworkConstantSingleton.getINSTANCE().getRequestJsonFolderPath()+"request.json");

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(jsonString).getAsJsonObject();
        RequestSpecification requestSpecification = RequestBuilder.buildRequestForPostCalls().body(jsonString);
        Response response = requestSpecification.post("/clients");
        System.out.println(jsonObject.get("id"));

        if(response.getStatusCode() != 201){
            RequestBuilder.buildRequestForPostCalls().pathParams("id",jsonObject.get("id").getAsInt()).delete("/clients/{id}");
            response = requestSpecification.post("/clients");
        }
        Path outputFile = Paths.get(FrameworkConstantSingleton.getINSTANCE().getResponseJsonFolderPath()+ method.getName()+"response.json").getParent();
        Files.createDirectories(outputFile);
        ApiUtils.storeStringAsJsonFile(FrameworkConstantSingleton.getINSTANCE().getResponseJsonFolderPath()+ method.getName()+"response.json",response);
        ExtentLogger.logResponse(response.prettyPrint());
        assertThat(response.getStatusCode()).isEqualTo(201);





    }
}


