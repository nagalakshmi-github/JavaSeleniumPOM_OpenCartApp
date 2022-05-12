package com.qa.opencart.tests;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void registerPageSetup() {
		registrationPage = loginPage.navigateToRegisterPage();
	}

	@Test
	public void registrationPageTitleTest() {
		Assert.assertEquals(registrationPage.registrationPageTitle(), Constants.REGISTER_PAGE_TITLE);
	}

	@Test
	public void registrationHeaderTest() {
		Assert.assertEquals(registrationPage.registrationPageHeader(), Constants.REGISTER_PAGE_TITLE);
	}

	public String getRandomEmail() {
		Random randomGenerator=new Random();
		String email="groupautomation"+randomGenerator.nextInt(1000)+"@gmail.com";
		return email;
	}

	@DataProvider(name = "registrationFormData")
	public Object[][] fillRegistrationFormData() {
		return ExcelUtil.getTestData(Constants.REGISTER_FORM_EXCEL_SHEET_NAME);
	}

	@Test(dataProvider = "registrationFormData")
	public void registrationTest(String fname, String lname, String telNo, String pwd, String subscribe) {
		Assert.assertTrue(registrationPage.fillRegistrationForm(fname, lname, getRandomEmail(), telNo, pwd, subscribe),
				"User failed to register.....");
	}
}
