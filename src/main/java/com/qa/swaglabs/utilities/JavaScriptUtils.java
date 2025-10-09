package com.qa.swaglabs.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Step;

public class JavaScriptUtils {
	private WebDriver driver;
	private JavascriptExecutor js;
	
	private static final Logger log= LogManager.getLogger(JavaScriptUtils.class);
	
	public JavaScriptUtils(WebDriver driver) {
		this.driver=driver;
		js=(JavascriptExecutor) driver;
	}
	
	
	public void generateAlert(String msg) {
		log.info("Generate an alert using javascript");
		js.executeScript("alert("+ msg +")");
	}
	
	public void scrollToElement(By locator) {
		log.info("Scroll to the element using javascript");
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
	}
	
}
