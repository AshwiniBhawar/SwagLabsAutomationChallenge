package com.qa.swaglabs.utilities;

import java.time.Duration;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qa.swaglabs.exceptions.ElementException;

public class ElementUtils {
	
	private WebDriver driver;
	private Select select;
	
	private static final Logger log= LogManager.getLogger(ElementUtils.class);
	public ElementUtils(WebDriver driver) {
		this.driver=driver;
	}
	
	public void doSendKeys(By locator, String value) {
		log.info("Send value: "+value+" in the textbox :"+locator);
		if(value==null || value.isEmpty() || value.isBlank()) {
			log.error("Provided value : " + value + " is null or blank or empty...");
			throw new ElementException("Provided value : " + value + " is null or blank or empty...");
		}
		WebElement element=getElement(locator);
		element.clear();
		element.sendKeys(value);
		
	}
	
	public WebElement getElement(By locator) {
		log.info("Find the element using :"+locator);
		WebElement element=driver.findElement(locator);
		return element;
	}
	
	public List<WebElement> getElements(By locator) {
		log.info("Find the element using :"+locator);
		List<WebElement> element=driver.findElements(locator);
		return element;
	}
	
	public String getElementText(By locator) {
		log.info("Get the text of element using :"+locator);
		return getElement(locator).getText();
	}
	
	public void doClick(By locator) {
		log.info("Click on the element using :"+locator);
		getElement(locator).click();
	}
	
	//******************************************dropdown**********************************************//
	
	private void selectElement(By locator) {
		log.info("Select the dropdown :"+locator);
		select = new Select(getElement(locator));
	}
	
	public void doSelectByIndex(By locator, int index) {
		log.info("Select the dropdown field :"+locator+" using index");
		selectElement(locator);
		select.selectByIndex(index);
	}
	
	public void doSelectByVisibleText(By locator, String eleText) {
		log.info("Select the dropdown field :"+locator+" using visible text");
		selectElement(locator);
		select.selectByVisibleText(eleText);
	}
	
	public void doSelectByContainsVisibleText(By locator, String value) {
		log.info("Select the dropdown field :"+locator+" using fraction visible text");
		selectElement(locator);
		select.selectByContainsVisibleText(value);
	}
	
	public void doSelectByValue(By locator, String value) {
		log.info("Select the dropdown field :"+locator+" using value");
		selectElement(locator);
		select.selectByValue(value);
	}
	
	
	//*************************************Wait Utility************************************//
	
	public void acceptAlert(int timeout) {
		log.info("Accept an alert if any");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.alertIsPresent()).accept();
		}
		catch(TimeoutException e) {
			log.error("Unable to find an alert within given period");
		}
		catch(NoAlertPresentException e){
			log.error("Alert is not present");
		}
	}
	
	public WebElement waitForElementPresence(By locator, int timeout) {
		log.info("Wait for element to be present :"+locator);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return element;
	}
	
	public WebElement waitForElementVisibile(By locator, int timeout) {
		log.info("Wait for element to be visible :"+locator);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}
	
	public List<WebElement> waitForElementsVisibile(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		List<WebElement> element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		return element;
	}
	
	public String waitForElementText(By locator, int timeout) {
		log.info("Wait for element to be visible :"+locator);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		String text=null;
		try {
		text= wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
		}catch(TimeoutException e) {
			log.error("Element not found on the page: " + locator);
			throw new ElementException("Element not found on the page: " + locator);
		}	
		log.info("Text of the element is :"+text);
		return text;
	}
	
	public void waitForElementClick(By locator, int timeout) {
		log.info("Wait for element to be clickable :"+locator);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();	
		}catch (TimeoutException e) {
			log.error("Element not found on the page: " + locator);
			throw new ElementException("Element not found on the page: " + locator);
		}
	}
	
	public void waitForElementClick(WebElement element, int timeout) {
		log.info("Wait for element to be clickable :"+element);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();	
		}catch (TimeoutException e) {
			log.error("Element not found on the page: " + element);
			throw new ElementException("Element not found on the page: " + element);
		}
	}
	
	public String waitForPagetitle(String expectedTitleValue, int timeout) {
		log.info("Wait for title of the page :"+expectedTitleValue);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.titleIs(expectedTitleValue));
		} catch (TimeoutException e) {
			log.error("Expected title value : " + expectedTitleValue + " is not present");
			throw new ElementException("Expected title value : " + expectedTitleValue + " is not present");
		}	
		return driver.getTitle();
	}
	
	
	public boolean waitForElementToBeDisplayed(By locator, int timeout) {
		log.info("Wait for element to be displayed of the page :"+locator);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
		} catch (TimeoutException e) {
			log.error("Element not found on the page: " + locator);
			throw new ElementException("Element not found on the page: " + locator);
		}	
	}
	
	//******************************************Relative Locators**********************************************//
	
	public WebElement rightOfRelativeLocator(By withLocator, By rightOfLocator) {
		log.info("Select a rightOf locator:" +rightOfLocator+"of locator :"+withLocator);
		try {
			return driver.findElement(RelativeLocator.with(withLocator).toRightOf(rightOfLocator));
		} catch (NoSuchElementException e) {
			throw new ElementException("Element not found on the page- with locator :" + withLocator+ "rightOf locator :"+rightOfLocator);
		}	
	}
	
	public WebElement leftOfRelativeLocator(By withLocator, By leftOfLocator) {
		log.info("Select a leftOf locator:" +leftOfLocator+"of locator :"+withLocator);
		try {
			return driver.findElement(RelativeLocator.with(withLocator).toLeftOf(leftOfLocator));
		} catch (NoSuchElementException e) {
			log.error("Element not found on the page- with locator :" + withLocator+ "leftOf locator :"+leftOfLocator);
			throw new ElementException("Element not found on the page- with locator :" + withLocator+ "leftOf locator :"+leftOfLocator);
		}	
	}
	
	public WebElement aboveRelativeLocator(By withLocator, By aboveLocator) {
		log.info("Select a above locator:" +aboveLocator+"of locator :"+withLocator);
		try {
			return driver.findElement(RelativeLocator.with(withLocator).above(aboveLocator));
		} catch (NoSuchElementException e) {
			log.error("Element not found on the page- with locator :" + withLocator+ "above locator :"+aboveLocator);
			throw new ElementException("Element not found on the page- with locator :" + withLocator+ "above locator :"+aboveLocator);
		}	
	}
	
	public WebElement belowRelativeLocator(By withLocator, By belowLocator) {
		log.info("Select a below locator:" +belowLocator+"of locator :"+withLocator);
		try {
			return driver.findElement(RelativeLocator.with(withLocator).below(belowLocator));
		} catch (NoSuchElementException e) {
			log.error("Element not found on the page- with locator :" + withLocator+ "below locator :"+belowLocator);
			throw new ElementException("Element not found on the page- with locator :" + withLocator+ "below locator :"+belowLocator);
		}	
	}
	
	public WebElement nearRelativeLocator(By withLocator, By nearLocator) {
		log.info("Select a nearest locator:" +nearLocator+"of locator :"+withLocator);
		try {
			return driver.findElement(RelativeLocator.with(withLocator).near(nearLocator));
		} catch (NoSuchElementException e) {
			log.error("Element not found on the page- with locator :" + withLocator+ "near locator :"+nearLocator);
			throw new ElementException("Element not found on the page- with locator :" + withLocator+ "near locator :"+nearLocator);
		}	
	}
}
