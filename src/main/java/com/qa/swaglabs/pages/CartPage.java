package com.qa.swaglabs.pages;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.swaglabs.constants.AppConstants;
import com.qa.swaglabs.utilities.ElementUtils;

public class CartPage {
	private WebDriver driver;
	private ElementUtils eUtil;

	private static final Logger log = LogManager.getLogger(CartPage.class);

	public CartPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtils(driver);
	}

	private static final By productPriceLoc = By.xpath("//div[@class='inventory_item_price']");
	private static final By productNameLoc = By.xpath("//div[contains(@class,'inventory_item_name')]");
	private static final By cartItemsLoc = By.xpath("//div[@class='cart_item']");
	private static final By checkoutButtonLoc = By.xpath("//button[text()='Checkout']");
	private static final By cartBadgeCount = By.xpath("//span[@class='shopping_cart_badge']");
	private static final By continueShoppingBtnLoc = By.id("continue-shopping");
	private static final By removeBtnLoc = By.xpath("//button[text()='Remove']");

	public List<String> validateCartDetails() {
		log.info("Capture the products name and price from cart page");
		List<String> cartPageProductDetails = new ArrayList<String>();

		List<WebElement> productName = eUtil.waitForElementsVisibile(productNameLoc, AppConstants.DEFAULT_MEDIUM_WAIT);
		List<WebElement> priceName = eUtil.waitForElementsVisibile(productPriceLoc, AppConstants.DEFAULT_MEDIUM_WAIT);
		List<WebElement> cartItems = eUtil.waitForElementsVisibile(cartItemsLoc, AppConstants.DEFAULT_MEDIUM_WAIT);
		log.info("Size of items in the cart is :" + cartItems.size());
		for (int i = 0; i < cartItems.size(); i++) {
			cartPageProductDetails.add(productName.get(i).getText());
			cartPageProductDetails.add(priceName.get(i).getText());
		}
		log.info("Cart page products details with name and price: " + cartPageProductDetails);

		return cartPageProductDetails;

	}

	public CheckOutInformationPage clickOnCheckoutButton() {
		log.info("Click on Checkout button");
		eUtil.waitForElementVisibile(checkoutButtonLoc, AppConstants.DEFAULT_MEDIUM_WAIT).click();
		return new CheckOutInformationPage(driver);
	}

	public boolean removeItemsFromCart(int removeItemCount) {
		log.info("Remove products from the cart");
		String originalCount = eUtil.waitForElementText(cartBadgeCount, AppConstants.DEFAULT_MEDIUM_WAIT);

		for (int i = 1; i <= removeItemCount; i++) {
			By removeBtnLoc = By.xpath("(//button[text()='Remove'])[" + i + "]");
			eUtil.waitForElementClick(removeBtnLoc, AppConstants.DEFAULT_SHORT_WAIT);
		}
		String updatedCount = eUtil.waitForElementText(cartBadgeCount, AppConstants.DEFAULT_MEDIUM_WAIT);
		Integer updatedCartCount = Integer.parseInt(updatedCount);
		log.info("Products initial count present in the cart is :" + originalCount);
		log.info("Products count after removing :" + removeItemCount + " item/items from the cart is :"+ updatedCartCount);
		if (updatedCartCount == Integer.parseInt(originalCount) - 1) {
			return true;
		}
		return false;
	}

	public CartPage removeAllExistingProductsFromCart() throws InterruptedException {
		log.info("Remove all items from the cart");
		Thread.sleep(2000);
		try {
			int size = eUtil.waitForElementsVisibile(cartItemsLoc, AppConstants.DEFAULT_SHORT_WAIT).size();
			while(size>0) {
				log.info("Items present in the cart:" + size);
				eUtil.getElements(removeBtnLoc).get(0).click();
				size=eUtil.waitForElementsVisibile(cartItemsLoc, AppConstants.DEFAULT_SHORT_WAIT).size(); 
			}
		} 
		catch (TimeoutException e) {
			log.error("Cart is empty");
		}
		return this;
	}

	public ProductsPage clickContinueShoppingBtn() {
		log.info("Click on continue shopping button");
		eUtil.waitForElementClick(continueShoppingBtnLoc, AppConstants.DEFAULT_SHORT_WAIT);
		return new ProductsPage(driver);
	}

}
