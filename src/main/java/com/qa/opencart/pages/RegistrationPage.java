package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {
	private ElementUtil elementUtil;
	private By registerLink = By.xpath("//div[@class='list-group']/a[text()='Register']");
	private By registerAccountHeader = By.xpath("//div[@id='content']/h1");
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By emailId = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPwd = By.id("input-confirm");
	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");
	private By agreeTermsChkbox = By.xpath("//input[@name='agree' and @type='checkbox']");
	private By continueBtn = By.xpath("//input[@value='Continue' and @type='submit']");
	private By sucessMessg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");

	public RegistrationPage(WebDriver driver) {
		elementUtil = new ElementUtil(driver);
	}

	public String registrationPageTitle() {
		return elementUtil.waitForTitleContains(Constants.REGISTER_PAGE_TITLE, 5);
	}

	public String registrationPageHeader() {
		return elementUtil.doGetText(registerAccountHeader);
	}

	public boolean fillRegistrationForm(String fname, String lname, String email, String telNo, String pwd,
			String subscribe) {
		elementUtil.doSendKeys(firstName, fname);
		elementUtil.doSendKeys(lastName, lname);
		elementUtil.doSendKeys(emailId, email);
		elementUtil.doSendKeys(telephone, telNo);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doSendKeys(confirmPwd, pwd);

		if (subscribe == "Yes") {
			elementUtil.doClick(subscribeYes);
		} else {
			elementUtil.doClick(subscribeNo);
		}

		elementUtil.doClick(agreeTermsChkbox);
		elementUtil.doClick(continueBtn);

		String mesg = elementUtil.isElementVisible(sucessMessg, 5).getText();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (mesg.contains(Constants.REGISTER_SUCCESS_MESSG)) {
			elementUtil.doClick(logoutLink);
			elementUtil.doClick(registerLink);
			return true;
		}
		return false;
	}
}
