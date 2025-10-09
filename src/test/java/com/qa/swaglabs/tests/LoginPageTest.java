package com.qa.swaglabs.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.swaglabs.base.BaseTest;
import com.qa.swaglabs.constants.AppConstants;
import com.qa.swaglabs.utilities.ExcelUtils;

public class LoginPageTest extends BaseTest{
	
		
	@Test(priority=Integer.MAX_VALUE)
	public void performLoginUsingStdUser() {
		boolean actualTitle=loginPage.performUserLogin(prop.getProperty("username"), prop.getProperty("password")).isSwagLabsHeaderExist();
		Assert.assertTrue(actualTitle);
	}
	
	@DataProvider
	public Object[][] testDataUsingExcel(){
		return ExcelUtils.getExcelData("SwagLabs", "LoginUserData");
	}
	
		
	@Test(dataProvider="testDataUsingExcel")
	public void performLoginUsingExcelData(String username, String password) {
		loginPage.performUserLogin(username, password).clickLogout();
	}
	

}
