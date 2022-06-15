package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GoogleTest extends BaseTest {
	@DataProvider
	public Object[][] getData() {
		return new Object[][] { { "selenium tutorial" } };
	}
	
	
	@DataProvider
	public Object[][] validateData() {
		return new Object[][] { { "selenium tutorial" },{"Selenium stuff"} };
	}

	@Test(dataProvider = "getData")
	public void clickOnSearch(String txt) {
		googleSearch.doClickSearchField(txt);
	}
	
	@Test
	public void validateListNotEmpty() {
		Assert.assertTrue(googleSearch.getGoogleSearchSuggestionsList().size()>0);
	}
	
	@Test(dataProvider = "validateData")
	public void verifySearchTxtAvailableOrNot(String txt) {
		Assert.assertTrue(googleSearch.selectSearchTxtFromDrpdwn(txt));
	}
}
