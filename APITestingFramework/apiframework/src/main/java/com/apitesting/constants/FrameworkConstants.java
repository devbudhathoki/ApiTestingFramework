package com.apitesting.constants;

public class FrameworkConstants {

    private FrameworkConstants(){}

    private static final String EXTENT_REPORT_PATH=System.getProperty("user.dir")+"/extent-test-output/";
    private static String extentReportFilePath ="";
    private static  final String OPENAI_API_KEY_FILE = "/tmp/openai";
    private static  final String OPENAI_API_REQUESTS_PATH = System.getProperty("user.dir")+"/src/test/resources/jsons/";

    public static String getOpenAICredentialFile(){
        return OPENAI_API_KEY_FILE;
    }

    public static String getRequestFolder(){
        return OPENAI_API_REQUESTS_PATH;
    }

    public static String getExtentReportFilePath(){
        if (extentReportFilePath.isEmpty()){
            extentReportFilePath=createReportPath();
        }

        return extentReportFilePath;
    }

    public static String createReportPath(){
        return EXTENT_REPORT_PATH+"/index.html";
    }
}
