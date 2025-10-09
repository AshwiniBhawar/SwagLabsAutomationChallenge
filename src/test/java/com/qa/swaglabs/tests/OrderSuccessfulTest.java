package com.qa.swaglabs.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.swaglabs.base.BaseTest;
import com.qa.swaglabs.constants.AppConstants;

public class OrderSuccessfulTest extends BaseTest{
	
	@BeforeClass
	public void performLoginUsingStdUserTest() {
		productsPage=loginPage.performUserLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] checkOutUserData() {
		return new Object[][]{
			{3,"Automation","selenium","12345"}
		};
	}
	
	@Test(dataProvider="checkOutUserData")
	public void validateItemsTotalPriceTest(int itemCount, String firstName, String lastName, String postalCode) {
		productsPage.addHighToLowProductsToTheCart(itemCount);
		String actualResult=productsPage.clickOnCartLink().clickOnCheckoutButton()
											.enterDetailsAndClickContinue(firstName, lastName, postalCode).validateProductsPaymentDetails().orderSuccMsg();
		Assert.assertEquals(actualResult, AppConstants.ORDER_SUCC_MSG);
	}
}
