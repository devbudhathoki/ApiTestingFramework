package com.apitesting.reports;

import com.aventstack.extentreports.ExtentTest;

import java.util.Objects;

public class ExtentManager {

    private ExtentManager() {

    }

    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExtentTest getTest() {
        return  extentTest.get();

    }

    public static void setExtentTest(ExtentTest test) {
        if(Objects.nonNull(test)) {
            extentTest.set(test);
        }
    }
    static void unload(){
        extentTest.remove();
    }
}
