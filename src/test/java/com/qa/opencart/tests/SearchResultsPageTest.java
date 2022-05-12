package com.qa.opencart.tests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchResultsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@DataProvider
	public Object[][] productSelectData(){
		return new Object[][] {
			{"MacBook","MacBook Pro" }, {"iMac","iMac"}, {"Apple","Apple Cinema 30\""}
		};
	}
	
	@Test(dataProvider = "productSelectData")
	public void productDetailsTest(String productName, String mainProductName) {
		searchResultsPage=accountsPage.doSearch(productName);
		softAssert.assertEquals(searchResultsPage.searchTxtHeader(), "Search - "+productName);
		softAssert.assertTrue(searchResultsPage.getProductsListCount()>0);
		productInfoPage =searchResultsPage.selectProduct(mainProductName);
		softAssert.assertEquals(productInfoPage.getProductHeaderText(),mainProductName);
		
		softAssert.assertAll();
	}
}
