package com.qa.opencart.tests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic - 100: design open cart application")
@Story("US - 101: design login page with different features")
public class LoginPageTest extends BaseTest {
	
	@Description("Login page title test...")
	@Severity(SeverityLevel.TRIVIAL)
	@Test
	public void loginPageTitleTest() {
		String actualPageTitle = loginPage.getLoginPageTitle();
		System.out.println("Actual page title is:" + actualPageTitle);
		Assert.assertEquals(actualPageTitle, Constants.LOGIN_PAGE_TITLE);
	}

	@Description("Forgot password link test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Description("Application login test with correct username and password...")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void loginTest() {
		accountsPage=loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		Assert.assertEquals(accountsPage.getAccountPageTitle(),Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Description("Navigate to registration page test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void navigateToRegisterPageTest() {
		registrationPage=loginPage.navigateToRegisterPage();
		Assert.assertEquals(registrationPage.registrationPageTitle(), Constants.REGISTER_PAGE_TITLE);
	}
}
