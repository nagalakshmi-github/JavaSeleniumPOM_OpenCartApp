package com.qa.opencart.tests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class AccountLogoutPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		accountLogoutPage=accountsPage.logout();
	}
	
	@Test
	public void getAccLogoutPageTitleTest() {
		Assert.assertEquals(accountLogoutPage.accountLogoutPageTitle(),Constants.ACCOUNT_LOGOUT_PAGE_TITLE);
	}
	
	@Test
	public void getAccLogoutPageHeaderTest() {
		Assert.assertEquals(accountLogoutPage.accountLogoutPageHeader(),Constants.ACCOUNT_LOGOUT_PAGE_TITLE);
	}
}
