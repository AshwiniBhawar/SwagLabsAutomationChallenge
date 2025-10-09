package com.qa.swaglabs.tests;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.swaglabs.base.BaseTest;
import com.qa.swaglabs.utilities.ExcelUtils;

public class InvalidUserLoginTest extends BaseTest{

	@DataProvider
	public Object[][] testDataUsingExcel(){
		return ExcelUtils.getExcelData("SwagLabs", "InvalidUserData");
	}
	
	@Test(dataProvider="testDataUsingExcel")
	public void performInvalidLoginTest(String username, String password){
		boolean result=loginPage.performInvalidUserNamePassword(username, password);
		Assert.assertTrue(result);
	}
}
