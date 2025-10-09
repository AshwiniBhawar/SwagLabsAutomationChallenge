package com.qa.swaglabs.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.swaglabs.constants.AppConstants;
import com.qa.swaglabs.driverfactory.DriverFactory;
import com.qa.swaglabs.utilities.ElementUtils;

public class CheckOutCompletePage {
	private WebDriver driver;
	private ElementUtils eUtil;
	
	private static final Logger log= LogManager.getLogger(CheckOutCompletePage.class);
	
	public CheckOutCompletePage(WebDriver driver){
		this.driver=driver;
		eUtil= new ElementUtils(driver);
	}
	
	private static final By succMsgLoc= By.tagName("h2");
	
	public String orderSuccMsg() {
		String result=eUtil.waitForElementVisibile(succMsgLoc, AppConstants.DEFAULT_MEDIUM_WAIT).getText();
		log.info("Order successful msg is :"+result);
		return result;
	}
}
