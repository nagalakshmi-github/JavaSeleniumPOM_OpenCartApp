package com.qa.opencart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountLogoutPage;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.GoogleSearch;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
	public WebDriver driver;
	public DriverFactory factory;
	public Properties prop;
	public LoginPage loginPage;
	public AccountsPage accountsPage;
	public AccountLogoutPage accountLogoutPage;
	public RegistrationPage registrationPage;
	public SearchResultsPage searchResultsPage;
	public ProductInfoPage productInfoPage;
	public SoftAssert softAssert = new SoftAssert();

	public GoogleSearch googleSearch;
	
	@Parameters({"browser","browserversion"})
	@BeforeTest
	public void setUp(String browserName, String browserVersion) {
		factory = new DriverFactory();
		prop = factory.init_prop();
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
		}
		driver = factory.init_driver(prop);
		//loginPage = new LoginPage(driver);
		googleSearch = new GoogleSearch(driver);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
