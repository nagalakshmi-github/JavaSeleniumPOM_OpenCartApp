package com.qa.opencart.tests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test
	public void accountPageTitleTest() {
		Assert.assertEquals(accountsPage.getAccountPageTitle(), Constants.ACCOUNTS_PAGE_TITLE);
	}

	@Test
	public void accountsPageHeaderTest() {
		Assert.assertEquals(accountsPage.getAccountsPageHeader(), Constants.ACCOUNTS_PAGE_HEADER);
	}

	@Test
	public void accountSecListTest() {
		Assert.assertEquals(accountsPage.getAccountSecList(), Constants.getExpectedSecList());
	}

	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accountsPage.isLogoutLinkExist());
	}

	@Test
	public void searchExistTest() {
		Assert.assertTrue(accountsPage.isSearchExist());
	}

	@DataProvider
	public Object[][] productData(){
		return new Object[][] {
			{"MacBook"}, {"iMac"}, {"Apple"}
		};
	}
	@Test(dataProvider = "productData")
	public void searchTest(String productName) {
		searchResultsPage=accountsPage.doSearch(productName);
		Assert.assertEquals(searchResultsPage.searchTxtHeader(), "Search - "+productName);
		Assert.assertTrue(searchResultsPage.getProductsListCount()>0,"No products found related to search");
	}

	@Test
	public void testLogout() {
		accountLogoutPage=accountsPage.logout();
		Assert.assertEquals(accountLogoutPage.accountLogoutPageTitle(), Constants.ACCOUNT_LOGOUT_PAGE_TITLE);
	}
	
	@DataProvider
	public Object[][] accountsPageRightPanelLinksData() {
		return new Object[][] { {"My Account"},{"Edit Account"},{"Password"},{"Address Book"},{"Wish List"},
								{"Order History"},{"Downloads"},{"Recurring payments"},{"Reward Points"},
								{"Returns"},{"Transactions"},{"Newsletter"},{"Logout"}};
	}
	
	@Test(dataProvider="accountsPageRightPanelLinksData")
	public void isAccountsPageRightPanelLinksExist(String linkTxt) {
		Assert.assertTrue(accountsPage.isAccountsPageLinksVisible(linkTxt));
	}
}

