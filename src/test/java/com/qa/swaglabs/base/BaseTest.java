package com.qa.swaglabs.base;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.swaglabs.driverfactory.DriverFactory;
import com.qa.swaglabs.pages.CartPage;
import com.qa.swaglabs.pages.LoginPage;
import com.qa.swaglabs.pages.ProductsPage;

public class BaseTest {

	WebDriver driver;
	DriverFactory df;
	
	private static final Logger log= LogManager.getLogger(BaseTest.class);
	
	protected Properties prop;
	protected LoginPage loginPage;
	protected ProductsPage productsPage;
	protected CartPage cartPage;
	
	@Parameters("browser")
	@BeforeTest
	public void setUp(@Optional("chrome") String browserName) {
		log.info("BeforeTest method is executed==>set up the browser");
		df= new DriverFactory();
		prop=df.initProp();
		
		if(browserName !=null) {
			log.info("Set the property for provided browser parameter");
			prop.setProperty("browser", browserName);
		}
		driver=df.initDriver(prop);
		loginPage= new LoginPage(driver);
	}
	
	@AfterTest
	public void tearDown() {
		log.info("AfterTest method is executed==>close the browser");
		driver.quit();
	}
	
	@AfterMethod
	public void attachScreenshot(ITestResult result) {
		log.info("Take a screenshot");
		if(!result.isSuccess()) {
			ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
		}
	}
}
