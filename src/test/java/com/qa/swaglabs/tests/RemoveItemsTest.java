package com.qa.swaglabs.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.swaglabs.base.BaseTest;

public class RemoveItemsTest extends BaseTest{

	@BeforeClass
	public void performLoginUsingStdUserTest() {
		productsPage=loginPage.performUserLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test()
	public void removeItemsFromCart(){
		productsPage.addHighToLowProductsToTheCart(2);
		boolean result=productsPage.clickOnCartLink().removeItemsFromCart(1);
		Assert.assertTrue(result, "Unable to remove items from the cart");
	}
}
