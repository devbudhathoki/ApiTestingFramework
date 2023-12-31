package com.apitesting.utils;

import com.apitesting.constants.FrameworkConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyUtils {

    private PropertyUtils(){}

    private static Properties properties = new Properties();
    private static Map<String,String> map = new HashMap<>();

    static {
        try (
            FileInputStream inputStream = new FileInputStream(FrameworkConstants.getOpenAICredentialFile())
        ) {
            properties.load(inputStream);
        }
        catch (IOException e){
            e.printStackTrace();
            System.exit(0);
        }
        properties.entrySet().forEach(e->map.put(String.valueOf(e.getKey()),String.valueOf(e.getValue())));
    }


    public static String getValue(String key){
        return map.get(key);
    }

}