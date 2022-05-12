package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountLogoutPage {
	public WebDriver driver;
	private ElementUtil elementUtil;
	private By accountLogoutPageHeader=By.cssSelector("div#content h1");
	
	public AccountLogoutPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	public String accountLogoutPageTitle() {
		return elementUtil.waitForTitleIs(Constants.ACCOUNT_LOGOUT_PAGE_TITLE, 5);
	}
	
	public String accountLogoutPageHeader() {
		return elementUtil.doGetText(accountLogoutPageHeader);
	}
}
