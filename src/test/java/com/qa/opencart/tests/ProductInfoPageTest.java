package com.qa.opencart.tests;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductInfoPageTest extends BaseTest {
	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test
	public void productInfoHeaderTest() {
		searchResultsPage = accountsPage.doSearch("MacBook");
		productInfoPage= searchResultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeaderText(),"MacBook Pro");
		
	}
	
	@Test
	public void productImgListCountTest() {
		searchResultsPage = accountsPage.doSearch("iMac");
		productInfoPage= searchResultsPage.selectProduct("iMac");
		Assert.assertEquals(productInfoPage.getProductImagesCount(),3);
	}
	
	@Test
	public void productInfoTest() {
		searchResultsPage = accountsPage.doSearch("MacBook");
		productInfoPage= searchResultsPage.selectProduct("MacBook Pro");
		Map<String,String> actProductInfoMap=productInfoPage.getProductInfo();
		actProductInfoMap.forEach((k,v) -> System.out.println(k + " : " + v));
		
		softAssert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductInfoMap.get("price"), "$2,000.00");

		softAssert.assertAll();
	}
}
