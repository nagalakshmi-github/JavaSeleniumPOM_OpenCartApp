package com.qa.opencart.tests;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoPageTest_WithDP extends BaseTest {
	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return ExcelUtil.getTestData(Constants.PRODUCT_SHEET_NAME);
	}

	@Test(dataProvider="getProductData")
	public void productInfoHeaderTest(String prodName, String mainProdName) {
		searchResultsPage = accountsPage.doSearch(prodName);
		productInfoPage= searchResultsPage.selectProduct(mainProdName);
		Assert.assertEquals(productInfoPage.getProductHeaderText(),mainProdName);
		
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
