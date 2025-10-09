package com.qa.swaglabs.constants;

import java.util.List;

public class AppConstants {
	public static final int DEFAULT_SHORT_WAIT= 5;
	public static final int DEFAULT_MEDIUM_WAIT= 10;
	public static final int DEFAULT_LONG_WAIT= 20;
	
	public static final String PAGE_TITLE="Swag Labs";
	
	public static final String INVALID_CRED_ERROR_MSG="Epic sadface: Username and password do not match any user in this service";
	public static final String USERNAME_BLANK_ERROR_MSG="Epic sadface: Username is required";
	public static final String PASSWORD_BLANK_ERROR_MSG="Epic sadface: Password is required";
	
	public static final String LOCKED_OUT_USER_ERROR_MSG="Epic sadface: Sorry, this user has been locked out.";
	
	public static final List<String> HAMBURGER_MENU_LIST= List.of("All Items","About","Logout","Reset App State");
	public static final List<String> HIGHEST_TWO_PRODUCTS_DETAILS= List.of("Sauce Labs Fleece Jacket", "$49.99", "Sauce Labs Backpack", "$29.99");
	public static final String ORDER_SUCC_MSG= "Thank you for your order!";
}
