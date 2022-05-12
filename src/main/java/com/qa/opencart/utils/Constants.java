package com.qa.opencart.utils;

import java.util.Arrays;
import java.util.List;

public class Constants {
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final String ACCOUNTS_PAGE_HEADER = "Your Store";
	public static List<String> getExpectedSecList() {
		return Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");
	}
	public static final String ACCOUNT_LOGOUT_PAGE_TITLE="Account Logout";
	public static final String REGISTER_PAGE_TITLE="Register Account";
	public final static String REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";
	public static final String REGISTER_FORM_EXCEL_SHEET_NAME="RegistrationPage_testData";
	public static final String PRODUCT_SHEET_NAME="ProductInfo";
}
