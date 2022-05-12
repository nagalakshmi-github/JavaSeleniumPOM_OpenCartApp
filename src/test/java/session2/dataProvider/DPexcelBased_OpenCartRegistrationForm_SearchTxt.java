package session2.dataProvider;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.ExcelUtil;

public class DPexcelBased_OpenCartRegistrationForm_SearchTxt extends BaseTest1 {
	By registerLink = By.linkText("Register");
	By firstName = By.id("input-firstname");
	By lastName = By.id("input-lastname");
	By email = By.id("input-email");
	By telephone = By.id("input-telephone");
	By password = By.id("input-password");
	By confirmPwd = By.id("input-confirm");
	By chkBox = By.xpath("//input[@type='checkbox' and @name='agree']");
	By submitBtn = By.xpath("//input[@type='submit' and @value='Continue']");
	By sucessMessg = By.cssSelector("div#content h1");
	By searchField = By.name("search");
	By logoutLink = By.linkText("Logout");

	public void doSendKeys(By locator, String value) {
		driver.findElement(locator).sendKeys(value);
	}

	@DataProvider()
	public Object[][] getRegistrationFieldsData() {
		return ExcelUtil.getTestData("RegistrationPage_testData");
	}

	@Test(dataProvider = "getRegistrationFieldsData")
	public void fillRegistrationForm(String fname, String lname, String emailId, String telNo, String pwd) {
		driver.findElement(registerLink).click();
		doSendKeys(firstName, fname);
		doSendKeys(lastName, lname);
		doSendKeys(email, emailId);
		doSendKeys(telephone, telNo);
		doSendKeys(password, pwd);
		doSendKeys(confirmPwd, pwd);
		driver.findElement(chkBox).click();
		driver.findElement(submitBtn).click();
		Assert.assertEquals(driver.findElement(sucessMessg).getText(), "Your Account Has Been Created!");
		driver.findElement(logoutLink).click();
	}

	/*
	 * @DataProvider() public Object[][] getSearchKeywordsTestData() { return new
	 * Object[][] { { "Imac" }, { "Apple" }, { "Tablet" } }; }
	 * 
	 * @Test(dataProvider = "getSearchKeywordsTestData") public void
	 * searchTestData(String searchForText) throws InterruptedException {
	 * driver.findElement(searchField).clear(); doSendKeys(searchField,
	 * searchForText); driver.findElement(searchField).sendKeys(Keys.ENTER);
	 * Thread.sleep(2000); }
	 */
}
