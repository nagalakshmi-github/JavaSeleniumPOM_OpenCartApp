package session2.dataProvider;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest1 {

	By errMsg = By.cssSelector("div.alert-danger");

	@DataProvider()
	public Object[][] negativeTestData() {
		return new Object[][] { 
								{ "naveenanimation20@gmail.com", "Selenium@12345" }, 
								{ "", "Selenium@12345" },
								{ "naveenanimation20@gmail.com", "" }, 
								{ "", "" } 
							};
	}

	@Test(dataProvider = "negativeTestData")
	public void doLogin(String uname,String pwd) throws InterruptedException {
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys(uname);
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys(pwd);
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Assert.assertTrue(driver.findElement(errMsg).isDisplayed());
	}
}
