package com.qa.swaglabs.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.swaglabs.constants.AppConstants;
import com.qa.swaglabs.utilities.ElementUtils;

public class CheckOutInformationPage {
	
	private WebDriver driver;
	private ElementUtils eUtil;
	
	private static final Logger log= LogManager.getLogger(CheckOutInformationPage.class);
	
	public CheckOutInformationPage(WebDriver driver){
		this.driver=driver;
		eUtil= new ElementUtils(driver);
	}
	
	private static final By firstNameLoc= By.id("first-name");
	private static final By lastNameLoc= By.id("last-name");
	private static final By postalCodeLoc= By.id("postal-code");
	private static final By continueBtnLoc=By.id("continue");
	
	public CheckOutOverviewPage enterDetailsAndClickContinue(String firstName, String lastName, String postalCode) {
		log.info("Enter firstName :"+firstName+ " lastName :"+lastName+ " postalCode :"+postalCode);
		eUtil.waitForElementVisibile(firstNameLoc, AppConstants.DEFAULT_MEDIUM_WAIT).sendKeys(firstName);
		eUtil.doSendKeys(lastNameLoc, lastName);
		eUtil.doSendKeys(postalCodeLoc, postalCode);;
		eUtil.waitForElementClick(continueBtnLoc, AppConstants.DEFAULT_MEDIUM_WAIT);
		return new CheckOutOverviewPage(driver);
	}

}
