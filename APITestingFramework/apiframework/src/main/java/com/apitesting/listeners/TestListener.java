package com.apitesting.listeners;

import com.apitesting.annotations.FrameworkAnnotation;
import com.apitesting.reports.ExtentLogger;
import com.apitesting.reports.ExtentReport;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class TestListener implements ITestListener, ISuiteListener {
    @Override
    public void onTestStart(ITestResult result) {

        ExtentReport.createTest(result.getName());

        String[] authors = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).author();
        ExtentReport.addAuthor(authors);

        String[] category = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).category();
        ExtentReport.addCategory(category);

        if(result.getName() == "getClientDetail") {
            ExtentLogger.pass(result.getName() + " with Parameters [id, name]: " + Arrays.asList(result.getParameters()) + " is passed");
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentLogger.pass(result.getName() +" is passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentLogger.fail(String.valueOf(result.getThrowable()));
    }

    @Override
    public void onStart(ITestContext context) {
       ExtentReport.initReports();
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReport.tearDownReports();
    }
}
