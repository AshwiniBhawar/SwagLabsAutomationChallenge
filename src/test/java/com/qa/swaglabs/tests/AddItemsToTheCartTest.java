package com.qa.swaglabs.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.swaglabs.base.BaseTest;

public class AddItemsToTheCartTest extends BaseTest{
	
	
	@BeforeClass
	public void performLoginUsingStdUserTest() {
		productsPage=loginPage.performUserLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
		
	@Test()
	public void addHigestProductsAndValidateDetailsOnCartPageTest() {
		List<String> actualData=productsPage.addHighToLowProductsToTheCart(2);
		List<String> expectedData=productsPage.clickOnCartLink().validateCartDetails();
		Assert.assertEquals(actualData, expectedData);
	}
	
}
