package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil elementUtil;
	private By email = By.id("input-email");
	private By pwd = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password11");
	private By registerLink = By.linkText("Register");
	private By logoutLink = By.linkText("Logout");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	@Step("Getting login page title")
	public String getLoginPageTitle() {
		return driver.getTitle();
	}

	@Step("Getting forgot password link exist or not")
	public boolean isForgotPwdLinkExist() {
		return elementUtil.doIsDisplayed(forgotPwdLink);
	}

	@Step("Application login using username: {0} and password: {1}")
	public AccountsPage doLogin(String un, String password) {
		System.out.println("Login with :" + un + " : " + password);
		elementUtil.doSendKeys(email, un);
		elementUtil.doSendKeys(pwd, password);
		elementUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	@Step("Navigate to registration page")
	public RegistrationPage navigateToRegisterPage() {
		if (elementUtil.doGetElements(logoutLink).size()>0) {
			elementUtil.doClick(logoutLink);
		}
		elementUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
}
