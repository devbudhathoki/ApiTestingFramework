package com.apitesting.utils;

import com.github.javafaker.Faker;

import java.util.Map;

public class FakerUtils {

    private FakerUtils(){

    }

    private static final Faker faker = new Faker();

    static int getNumber(int startvalue, int endvalue){
        return faker.number().numberBetween(startvalue,endvalue);
    }

    static String getName(){
        return  faker.name().firstName();
    }

    static int getAge(){
        return faker.number().numberBetween(18,100);
    }

    static Map<String, String> getAddress(){
        return Map.of("street", faker.address().streetAddress(),"city",faker.address().city(),"state",faker.address().state());
    }
}
