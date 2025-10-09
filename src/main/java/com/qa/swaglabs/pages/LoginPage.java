package com.qa.swaglabs.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.swaglabs.constants.AppConstants;
import com.qa.swaglabs.utilities.ElementUtils;

public class LoginPage {
	private WebDriver driver;
	private ElementUtils eUtil;
	
	private static final Logger log= LogManager.getLogger(LoginPage.class);
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eUtil= new ElementUtils(driver);
	}
	
	private final By emailIdLoc=By.id("user-name");
	private final By passwordLoc=By.id("password");
	private final By loginBtnLoc=By.name("login-button");
	private final By invalidCredErrorMsgLoc=By.xpath("//h3[@data-test='error']");
	
	
	public ProductsPage performUserLogin(String username, String password) {
		log.info("Enter username :"+username +" password :"+password);
		WebElement ele=eUtil.waitForElementVisibile(emailIdLoc, AppConstants.DEFAULT_MEDIUM_WAIT);
		ele.clear();
		ele.sendKeys(username);
		eUtil.doSendKeys(passwordLoc, password);
		eUtil.doClick(loginBtnLoc);
		return new ProductsPage(driver);
	}
	
	public String performLockedUserLogin(String username, String password) {
		log.info("Enter username :"+username +" password :"+password);
		WebElement ele=eUtil.waitForElementVisibile(emailIdLoc, AppConstants.DEFAULT_MEDIUM_WAIT);
		ele.clear();
		ele.sendKeys(username);
		eUtil.doSendKeys(passwordLoc, password);
		eUtil.doClick(loginBtnLoc);
		String errorText=eUtil.waitForElementText(invalidCredErrorMsgLoc,  AppConstants.DEFAULT_MEDIUM_WAIT);
		log.info("Error msg displayed for locked_out_user is :"+errorText);
		return errorText;
	}
	
	public boolean performInvalidUserNamePassword(String username, String password) {
		driver.navigate().refresh();
		if(username==null) {
			username="";
		}
		if(password==null) {
			password="";
		}
		log.info("Invalid application credentials username :" + username + " and password :" + password);
		WebElement emailEle = eUtil.waitForElementVisibile(emailIdLoc, AppConstants.DEFAULT_MEDIUM_WAIT);
		emailEle.clear();
		emailEle.sendKeys(username);
		
		WebElement passEle = eUtil.waitForElementVisibile(passwordLoc, AppConstants.DEFAULT_MEDIUM_WAIT);
		passEle.clear();
		passEle.sendKeys(password);
		eUtil.doClick(loginBtnLoc);
		String errorMessg = eUtil.waitForElementText(invalidCredErrorMsgLoc,AppConstants.DEFAULT_MEDIUM_WAIT);
		log.info("Invalid creds error messg: " + errorMessg);
		
		if (username.isBlank() && password.isBlank() && errorMessg.contains(AppConstants.USERNAME_BLANK_ERROR_MSG)) {
			return true;
		}
		
		else if (username.isBlank() && !password.isBlank() && errorMessg.contains(AppConstants.USERNAME_BLANK_ERROR_MSG)) {
			return true;
		}
		
		else if (!username.isBlank() && password.isBlank() && errorMessg.contains(AppConstants.PASSWORD_BLANK_ERROR_MSG)) {
			return true;
		}
		
		else if (!username.isBlank() && !password.isBlank() && errorMessg.contains(AppConstants.INVALID_CRED_ERROR_MSG)) {
			return true;
		}
		
		return false;
	}
	
}
