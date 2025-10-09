package com.qa.swaglabs.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.swaglabs.base.BaseTest;

public class BrokenImageTest extends BaseTest{
		
		@BeforeClass
		public void performLoginUsingStdUserTest() {
			productsPage=loginPage.performUserLogin("problem_user", "secret_sauce");
		}
		
		@Test
		public void validateBrokenImageProductDetailsTest() {
			boolean result=productsPage.validateTheBrokenImageProductDetails("Sauce Labs Backpack");
			Assert.assertTrue(result, "The product data on home page is not equal to the product details page");
		}
		
		@Test
		public void validateAddToCartBtnTest() {
			boolean result=productsPage.validateAddToCartBtn("Sauce Labs Backpack");
			Assert.assertTrue(result, "The button click is not working");
		}
}
