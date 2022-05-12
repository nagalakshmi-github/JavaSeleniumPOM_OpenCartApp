package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	public WebDriver driver;
	private ElementUtil elementUtil;
	private By searchTxtHeader = By.xpath("//div[@id='content']/h1");
	private By productResults=By.cssSelector("div.caption a");
	
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(driver);
	}
	
	public String searchTxtHeader() {
		return elementUtil.doGetText(searchTxtHeader);
	}
	
	
	public int getProductsListCount(){
		int resultsCount= elementUtil.waitForElementsToBeVisible(productResults, 5).size();
		System.out.println("The search product count: "+resultsCount);
		return resultsCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		List<WebElement> searchList=elementUtil.waitForElementsToBeVisible(productResults, 5);
		for(WebElement we:searchList) {
			if(we.getText().equals(productName)) {
				we.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
}
