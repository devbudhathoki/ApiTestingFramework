package com.apitesting.utils;

import com.apitesting.entity.Address;
import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Map;

public class RandomUtils {

    private RandomUtils(){}

    public static int getId(){
        return FakerUtils.getNumber(3,1000);
    }

    public static String getName(){
        return FakerUtils.getName().toLowerCase();
    }

    public static String getAge(){
        return String.valueOf(FakerUtils.getAge());
    }

    public static Address getAddress() {
        Map randomAddress = FakerUtils.getAddress();

        Address address = new Address();
        address.setCity(randomAddress.get("city").toString());
        address.setStreet(randomAddress.get("street").toString());
        address.setState(randomAddress.get("state").toString());
        return address;
    }
}
