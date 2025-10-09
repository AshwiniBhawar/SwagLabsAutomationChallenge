package com.qa.swaglabs.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.swaglabs.base.BaseTest;
import com.qa.swaglabs.constants.AppConstants;

public class LockedUserTest extends BaseTest{
	
	@Test
	public void performLoginUsingLockedUser() {
		String actualTitle=loginPage.performLockedUserLogin("locked_out_user", "secret_sauce");
		Assert.assertEquals(actualTitle, AppConstants.LOCKED_OUT_USER_ERROR_MSG);
	}
}
