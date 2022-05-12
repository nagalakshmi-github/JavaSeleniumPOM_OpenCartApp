package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	public WebDriver driver;
	private ElementUtil elementUtil;
	private By storeHeader = By.cssSelector("div#logo > h1");
	private By searchField = By.xpath("//input[@name='search']");
	private By searchButton = By.xpath("//div[@id='search']//button[@type='button']");
	private By leftPanelHeaders = By.cssSelector("div#content h2");
	private By logoutLink = By.linkText("Logout");
	private By accountsDrpdwn = By.xpath("//li[@class='dropdown']/a");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}

	public String getAccountPageTitle() {
		return elementUtil.waitForTitleIs(Constants.ACCOUNTS_PAGE_TITLE, 5);
	}

	public String getAccountsPageHeader() {
		return elementUtil.doGetText(storeHeader);
	}

	public List<String> getAccountSecList() {
		List<String> headersList = new ArrayList<String>();
		elementUtil.waitForElementsToBeVisible(leftPanelHeaders, 10).forEach(e -> headersList.add(e.getText()));
		System.out.println("headeres list:" + headersList);
		return headersList;
	}

	public boolean isSearchExist() {
		return elementUtil.doIsDisplayed(searchField);
	}

	public SearchResultsPage doSearch(String productName) {
		System.out.println("searching the product: " + productName);
		elementUtil.getElement(searchField).clear();
		elementUtil.doSendKeys(searchField, productName);
		elementUtil.doClick(searchButton);
		return new SearchResultsPage(driver);
	}
	
	public boolean isLogoutLinkExist() {
		boolean val=elementUtil.doIsDisplayed(logoutLink);
		System.out.println("val:"+val);
		return val;
	}

	public AccountLogoutPage logout() {
			elementUtil.doClick(accountsDrpdwn);
			List<WebElement> accDrpdwnValues = elementUtil
					.doGetElements(By.xpath("//li[@class='dropdown open']/a/following-sibling::ul/li"));
			for (WebElement e : accDrpdwnValues) {
				if (e.getText().equals("Logout")) {
					e.click();
					break;
				}
			}
			return new AccountLogoutPage(driver);
	}
	
	public boolean isAccountsPageLinksVisible(String linkTxt) {
		return elementUtil.doIsDisplayed(By.partialLinkText(linkTxt));
	}
}
