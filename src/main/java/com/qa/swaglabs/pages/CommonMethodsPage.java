package com.qa.swaglabs.pages;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.swaglabs.constants.AppConstants;
import com.qa.swaglabs.utilities.ElementUtils;

public class CommonMethodsPage {
	private WebDriver driver;
	private ElementUtils eUtil;
	
	private static final Logger log= LogManager.getLogger(LoginPage.class);
	
	public CommonMethodsPage(WebDriver driver) {
		this.driver=driver;
		eUtil= new ElementUtils(driver);
	}
	
	private static final By logoutLinkLoc=By.xpath("//a[@id='logout_sidebar_link']");
	private static final By hamburgerIconLoc=By.id("react-burger-menu-btn");
	private static final By cartLinkLoc=By.xpath("//a[@class='shopping_cart_link']");
	private static final By cartBadgeCountLoc=By.xpath("//a[@class='shopping_cart_link']/span");
	private static final By swaglabsHeaderLoc=By.xpath("//div[@class='app_logo']");
	
	public boolean isSwagLabsHeaderExist() {
		boolean result=eUtil.waitForElementToBeDisplayed(swaglabsHeaderLoc, AppConstants.DEFAULT_MEDIUM_WAIT);
		log.info("Swag Labs header is present on the page: "+result);
		return result;
	}
	
	public LoginPage clickLogout() {
		log.info("Click on logout button");
		eUtil.doClick(hamburgerIconLoc);
		eUtil.waitForElementClick(logoutLinkLoc, AppConstants.DEFAULT_MEDIUM_WAIT);
		return new LoginPage(driver);
	}
	
	public boolean checkCartBadgeCountForAddedProducts(int productCount) {
		String cartBadgeCount=eUtil.waitForElementVisibile(cartBadgeCountLoc, AppConstants.DEFAULT_MEDIUM_WAIT).getText();
		log.info("Products count present in the cart is :" +cartBadgeCount);
		
		if(Integer.parseInt(cartBadgeCount)==productCount) {
			return true;
		}
		return false;
	}
	
	public CartPage clickOnCartLink() {
		log.info("Click on cart link");
		eUtil.doClick(cartLinkLoc);
		return new CartPage(driver);
	}
}
 