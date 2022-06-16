package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class GoogleSearch {

	private By searchField = By.xpath("//input[@name=\"q\"]");
	private By googleSearchDrpdwn = By.xpath("//ul[@class='G43f7e']/li");
	public WebDriver driver = null;
	private ElementUtil elementUtil;

	public GoogleSearch(WebDriver driver) {
		this.driver = driver;
		this.elementUtil = new ElementUtil(driver);
	}

	public void doClickSearchField(String txt) {
		elementUtil.doSendKeys(searchField, txt);
		elementUtil.doClick(searchField);
	}

	public List<WebElement> getGoogleSearchSuggestionsList() {
		return elementUtil.waitForElementsToBeVisible(googleSearchDrpdwn, 5);
	}

	public boolean selectSearchTxtFromDrpdwn(String txt) {
		for (WebElement we : getGoogleSearchSuggestionsList()) {
			if (we.getText().equals(txt)) {
				we.click();
				System.out.println("element clicked");
				return true;
			} else {
				System.out.println("text searching for not found from google suggestions list");
			}
		}
		return false;
	}
}
