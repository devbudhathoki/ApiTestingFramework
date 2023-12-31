package com.apitesting.reports;

import com.apitesting.constants.FrameworkConstants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ExtentReport {

    private ExtentReport(){}

    private static ExtentReports extent;
    private static ExtentTest test;

    public static void initReports(){
        if(Objects.isNull(extent)) {
            extent = new ExtentReports();
            ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(FrameworkConstants.getExtentReportFilePath());
            extent.attachReporter(extentSparkReporter);
            extentSparkReporter.config().setTheme(Theme.STANDARD);
            extentSparkReporter.config().setDocumentTitle("API Testing Report");
            extentSparkReporter.config().setReportName("Sample API Testing Project");
        }
    }

    public static void tearDownReports(){
        if(Objects.nonNull(extent)) {
            extent.flush();
        }
        ExtentManager.unload();

        try{
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportFilePath()).toURI());
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void createTest(String name){
         test = extent.createTest(name);
         ExtentManager.setExtentTest(test);
    }

    public static void addAuthor(String[] authors) {
        for (String author : authors) {
            ExtentManager.getTest().assignAuthor(author);
        }
    }

    public static void addCategory(String[] categories){
        for(String category : categories){
            ExtentManager.getTest().assignAuthor(category);
        }
    }
}
