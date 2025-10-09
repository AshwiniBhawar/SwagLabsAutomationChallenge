package com.qa.swaglabs.driverfactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.swaglabs.error.AppErrors;
import com.qa.swaglabs.exceptions.FrameworkException;

public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	public OptionsManager op;
	
	private static final Logger log= LogManager.getLogger(DriverFactory.class);
	
	public static ThreadLocal<WebDriver> tlDriver= new ThreadLocal<WebDriver>();
		
	public WebDriver initDriver(Properties prop) {
		log.info("Initialize the driver");
		String browserName=prop.getProperty("browser");
		log.info("Browser value is: "+browserName);
		
		op= new OptionsManager(prop);
		
		switch(browserName.toLowerCase().trim()) {
		case "chrome":
			tlDriver.set(new ChromeDriver(op.getChromeOptions()));
			break;

		case "firefox":
			tlDriver.set(new FirefoxDriver(op.getFirefoxOptions()));
			break;
			
		case "edge":
			tlDriver.set(new EdgeDriver(op.getEdgeOptions()));
			break;
			
		case "safari":
			tlDriver.set(new SafariDriver());
			break;
			
		default:
			log.error("=========Invalid Broswer Passed :"+browserName+"==========");
			throw new FrameworkException(AppErrors.INVALID_BROWSER); 
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("baseurl"));
		
		return getDriver();
	}
	
	public Properties initProp() {
		log.info("Initialize the properties");
		prop= new Properties();
		FileInputStream fip=null;
		String envName=System.getProperty("env");
		log.info("Env name is: " + envName);
		try {
		if(envName==null) {
			fip = new FileInputStream("./src/test/resources/config/config.qa.properties");
		}
		else {
			switch (envName.toLowerCase().trim()) {
			case "qa":
				fip = new FileInputStream("./src/test/resources/config/config.qa.properties");
				break;
			case "dev":
				fip = new FileInputStream("./src/test/resources/config/config.dev.properties");
				break;
			case "uat":
				fip = new FileInputStream("./src/test/resources/config/config.uat.properties");
				break;
			case "prod":
				fip = new FileInputStream("./src/test/resources/config/config.prod.properties");
				break;
			default:
				throw new FrameworkException("========Invalid Environment=========");
			}
		}
			
		} catch (FileNotFoundException e) {
			log.error("File not found for "+envName+ " env");
			throw new FrameworkException("File not found for "+envName+" env");
		}
		try {
			prop.load(fip);
		} catch (IOException e) {
			log.error("Properties file is not successfully loaded");
			throw new FrameworkException("Properties file is not successfully loaded");
		}
		
		return prop;
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}


	public static File getScreenshotFile() {
		log.info("Take a screenshot using OutputType > file");
		TakesScreenshot ts= (TakesScreenshot) getDriver();
		return ts.getScreenshotAs(OutputType.FILE);
	}

}
