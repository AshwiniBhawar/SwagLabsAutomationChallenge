package com.qa.swaglabs.pages;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.swaglabs.constants.AppConstants;
import com.qa.swaglabs.exceptions.FrameworkException;
import com.qa.swaglabs.utilities.ElementUtils;
import com.qa.swaglabs.utilities.JavaScriptUtils;

public class CheckOutOverviewPage {
	private WebDriver driver;
	private ElementUtils eUtil;
	private JavaScriptUtils js;
	private static final Logger log= LogManager.getLogger(CheckOutOverviewPage.class);
	
	public CheckOutOverviewPage(WebDriver driver){
		this.driver=driver;
		eUtil= new ElementUtils(driver);
		js= new JavaScriptUtils(driver);
	}
	
	private static final By cartItems= By.xpath("//div[@class='cart_item']");
	private static final By priceNameLoc= By.xpath("//div[@class='inventory_item_price']");
	private static final By finishBtnLoc=By.id("finish");
	private static final By totalPriceLoc= By.xpath("//div[@class='summary_total_label']");
	private static final By itemsTotalPriceLoc=By.xpath("//div[@class='summary_subtotal_label']");
	private static final By taxPriceLoc=By.xpath("//div[@class='summary_tax_label']");
	
	public CheckOutCompletePage validateProductsPaymentDetails() {
		int itemsCount= eUtil.waitForElementsVisibile(cartItems, AppConstants.DEFAULT_MEDIUM_WAIT).size();
		log.info("Totla items for checkout :"+itemsCount);
		
		Double actualPrice=0.00;
		List<WebElement> list= eUtil.waitForElementsVisibile(priceNameLoc, AppConstants.DEFAULT_MEDIUM_WAIT);
		for(int i=0;i<itemsCount;i++) {
			actualPrice=actualPrice + Double.parseDouble(list.get(i).getText().replace("$", ""));
		}
		
		js.scrollToElement(itemsTotalPriceLoc);
		
		log.info("Actual total price of products excluding tax is :"+actualPrice);
		String expectedPrice=eUtil.getElementText(itemsTotalPriceLoc).split("\\$")[1];
		log.info("Expected total price of products excluding tax is :"+expectedPrice);
		
		double taxValue=actualPrice *8/100;
		double actualTaxValue = Math.round(taxValue * 100.0) / 100.0;
		log.info("Actual tax on the purchased products :"+actualTaxValue);
		String expectedTaxValue=eUtil.getElementText(taxPriceLoc).split("\\$")[1];
		log.info("Expected tax on the purchased products :"+expectedTaxValue);
		
		double totalPrice= actualPrice+ taxValue;
		double actualTotalPrice = Math.round(totalPrice * 100.0) / 100.0;
		log.info("Actual total price of products including tax is :"+actualTotalPrice);
		String expectedTotalPrice=eUtil.getElementText(totalPriceLoc).split("\\$")[1];
		log.info("Expected total price of products including tax is :"+expectedTotalPrice);
		
		
		if(actualPrice==Double.parseDouble(expectedPrice)){
			log.info("Products actual price :"+ actualPrice+" is equal to expected price :"+Double.parseDouble(expectedPrice));
		}
		else {
			log.error("Products actual price :"+ actualPrice+" is not equal to expected price :"+Double.parseDouble(expectedPrice));
			throw new FrameworkException("Products actual price :"+ actualPrice+" is not equal to expected price :"+Double.parseDouble(expectedPrice));
		}
			
		if(actualTaxValue==Double.parseDouble(expectedTaxValue)){
			log.info("Products tax price :"+actualTaxValue+" is equal to expected price :"+Double.parseDouble(expectedTaxValue));
		}
		else {
			log.error("Products tax price :"+actualTaxValue+" is not equal to expected price :"+Double.parseDouble(expectedTaxValue));
			throw new FrameworkException("Products tax price :"+actualTaxValue+" is not equal to expected price :"+Double.parseDouble(expectedTaxValue));
		}
		
		if(actualTotalPrice==Double.parseDouble(expectedTotalPrice)){
			log.info("Products total actual price :"+actualTotalPrice+" is equal to total expected price :"+Double.parseDouble(expectedTotalPrice));
		}
		else {
			log.error("Products total actual price :"+actualTotalPrice+" is not equal to total expected price :"+Double.parseDouble(expectedTotalPrice));
			throw new FrameworkException("Products total actual price :"+actualTotalPrice+" is not equal to total expected price :"+Double.parseDouble(expectedTotalPrice));
		}
		
		eUtil.waitForElementClick(finishBtnLoc, AppConstants.DEFAULT_MEDIUM_WAIT);
		return new CheckOutCompletePage(driver);
	}
}
