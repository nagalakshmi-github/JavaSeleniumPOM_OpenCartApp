package session2.dataProvider;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.ExcelUtil;

public class LoginPageCredentialsValidationsTest_DPwithExcel extends BaseTest1 {
	@DataProvider()
	public Object[][] getRegisterData(){
		return ExcelUtil.getTestData("LoginPage_testData");
	}
	
	@Test(dataProvider ="getRegisterData",expectedExceptions = NoSuchElementException.class )
	public void doLogin(String uname,String pwd) throws InterruptedException {
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys(uname);
		driver.findElement(By.id("input-password")).clear();
		driver.findElement(By.id("input-password")).sendKeys(pwd);
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Thread.sleep(1000);
		Assert.assertTrue(!driver.findElement(By.cssSelector("div.alert-danger")).isDisplayed(),"User failed Loged-in");
	}
}

