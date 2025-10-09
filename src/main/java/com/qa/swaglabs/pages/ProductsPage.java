package com.qa.swaglabs.pages;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.swaglabs.constants.AppConstants;
import com.qa.swaglabs.utilities.ElementUtils;

public class ProductsPage {
	private WebDriver driver;
	private ElementUtils eUtil;
	private CommonMethodsPage commonMethods;
	private static final Logger log= LogManager.getLogger(ProductsPage.class);
	
	public ProductsPage(WebDriver driver){
		this.driver=driver;
		eUtil= new ElementUtils(driver);
		commonMethods=new CommonMethodsPage(driver);
	}
	
	private static final By addToCartButtonLoc=By.xpath("//button[text()='Add to cart']");
	private static final By productsArrangeDropdownLoc= By.xpath("//select[@class='product_sort_container']");	
	
	public boolean isSwagLabsHeaderExist() {
		return commonMethods.isSwagLabsHeaderExist();
	}
	
	public LoginPage clickLogout() {
		return commonMethods.clickLogout();
	}
	
	public List<String> addHighToLowProductsToTheCart(int productCount) {
		log.info("Add "+productCount+" highest price products in to the cart");
		eUtil.doSelectByVisibleText(productsArrangeDropdownLoc, "Price (high to low)");
		List<String> productsDetails= new ArrayList<String>();
		
		for(int i=1;i<=productCount;i++) {
			By productNameLoc= By.xpath("(//div[@class='inventory_item_label']/a/div)["+i+"]");
			By productPriceLoc= By.xpath("(//div[@class='pricebar']//div)["+i+"]");
			productsDetails.add(eUtil.waitForElementText(productNameLoc, AppConstants.DEFAULT_MEDIUM_WAIT));
			productsDetails.add(eUtil.waitForElementText(productPriceLoc, AppConstants.DEFAULT_MEDIUM_WAIT));
			eUtil.rightOfRelativeLocator(addToCartButtonLoc, productPriceLoc).click();
		}
		
		log.info("Highest price products details with name and price from products page: "+productsDetails);
		return productsDetails;
	}
	
	public boolean checkCartBadgeCountForAddedProducts(int productCount) {
		log.info("Check cart badge count for added "+ productCount +" highest price products");
		addHighToLowProductsToTheCart(productCount);
		return commonMethods.checkCartBadgeCountForAddedProducts(productCount);
	}
	
	public CartPage clickOnCartLink() {
		return commonMethods.clickOnCartLink();
	}
	
	public boolean validateTheBrokenImageProductDetails(String productName) {
		 log.info("Validate the product details :" +productName);
		 By productNameHomePageLoc=By.xpath("//div[@class='inventory_item_name ' and text()='"+productName+"']");
		 By productPriceHomePageLoc= By.xpath("//div[text()='"+productName+"']/ancestor::div/following-sibling::div/div[@class='inventory_item_price']");
		 By imageLoc=By.xpath("//img[@alt='"+productName+"']");
		 
		 List<String> expectedList= new ArrayList<String>();
		 expectedList.add(eUtil.getElementText(productNameHomePageLoc));
		 expectedList.add(eUtil.getElementText(productPriceHomePageLoc));
		 expectedList.add(eUtil.getElement(imageLoc).getAttribute("src"));
		 log.info("Product data on home page :"+expectedList);
		 
		 eUtil.waitForElementClick(productNameHomePageLoc, AppConstants.DEFAULT_SHORT_WAIT);
		 
		 List<String> actualList= new ArrayList<String>();
		 By productNameDetailsPageLoc=By.xpath("//div[@data-test='inventory-item-name']");
		 By productPriceDetailsPageLoc= By.xpath("//div[@data-test='inventory-item-price']");
		 By imageDetailsPageLoc=By.xpath("//img[@class='inventory_details_img']");
		 
		 actualList.add(eUtil.waitForElementVisibile(productNameDetailsPageLoc, AppConstants.DEFAULT_MEDIUM_WAIT).getText());
		 actualList.add(eUtil.getElementText(productPriceDetailsPageLoc));
		 actualList.add(eUtil.getElement(imageDetailsPageLoc).getAttribute("src"));
		 log.info("Product data on the product details page :"+actualList);
		 
		 if(expectedList.equals(actualList)) {
			 log.info("Expected product list :"+ expectedList +" is equal to the actual product list :"+actualList);
			 return true;
		 }
		 log.info("Expected product list :"+ expectedList +" is not equal to the actual product list :"+actualList);
		 return false;
	}
	
	
	public boolean validateAddToCartBtn(String productName){
		log.info("Check add to cart button toggling");
		By btnLoc= By.xpath("//div[text()='"+productName+"']/ancestor::div/following-sibling::div/button");
		String firstClick= eUtil.getElementText(btnLoc); 
		log.info("First toggle: "+firstClick);
		eUtil.doClick(btnLoc);
		String secondClick= eUtil.getElementText(btnLoc);
		log.info("Second toggle: "+secondClick);
		eUtil.doClick(btnLoc);
		String thirdClick= eUtil.getElementText(btnLoc);
		log.info("Third toggle: "+thirdClick);
		
		if(firstClick.equals(thirdClick)) {
			log.info("Add to cart button toggling is working");
			return true;
		}
		log.info("Add to cart button toggling is not working");
		return false;
	}
	
	
		
}
