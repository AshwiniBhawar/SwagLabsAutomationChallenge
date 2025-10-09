package com.qa.swaglabs.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.qa.swaglabs.driverfactory.DriverFactory;
import io.qameta.allure.Attachment;

public class TestAllureListeners implements ITestListener{

	private static final Logger log= LogManager.getLogger(TestAllureListeners.class);
	
	private static String getTestMethodName(ITestResult result) {
		return result.getMethod().getConstructorOrMethod().getName();
	}
	
	//Text attachments for Allure
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}
	
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		log.info("I am onTestStart method : "+getTestMethodName(result));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		log.info("I am onTestSuccess method : "+getTestMethodName(result));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		log.info("I am onTestFailure method : "+getTestMethodName(result));
		if(!result.isSuccess()) {
			log.info("Screenshot captured for test case:" + getTestMethodName(result));
			saveScreenshotPNG(DriverFactory.getDriver());
		}
		saveTextLog(getTestMethodName(result) + " failed and screenshot taken!");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		log.info("I am onTestSkipped method : "+getTestMethodName(result));
	}

	@Override
	public void onStart(ITestContext context) {
		log.info("I am onStart method : "+context.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		log.info("I am onFinish method : "+context.getName());
	}
	

}
