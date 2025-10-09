package com.qa.swaglabs.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.swaglabs.base.BaseTest;

public class CartBadgeCountTest extends BaseTest{
	
	@BeforeClass
	public void performLoginUsingStdUserTest() {
		productsPage=loginPage.performUserLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void validateCartBadgeCountForAddedProductsTest() {
		Assert.assertTrue(productsPage.checkCartBadgeCountForAddedProducts(2), "products count added in the cart is not equal to cart badge count");
	}
}
