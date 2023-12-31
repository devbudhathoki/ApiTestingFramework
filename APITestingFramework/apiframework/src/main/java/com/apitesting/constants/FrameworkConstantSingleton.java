package com.apitesting.constants;

import lombok.Getter;

import java.util.Objects;

@Getter
public class FrameworkConstantSingleton {

    private static FrameworkConstantSingleton INSTANCE = null;

    private FrameworkConstantSingleton() {
    }

    public static synchronized FrameworkConstantSingleton getINSTANCE() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new FrameworkConstantSingleton();
        }
        return INSTANCE;
    }

    private  final String requestJsonFolderPath = System.getProperty("user.dir") + "/src/test/resources/jsons/";
    private  final String responseJsonFolderPath = System.getProperty("user.dir") + "/output/";
}

