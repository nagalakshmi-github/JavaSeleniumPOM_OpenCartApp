package session2.dataProvider;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.ExcelUtil;

public class DP_OpenCartSearch_TestDataInExcel extends BaseTest1 {
	By searchField = By.name("search");

	@DataProvider()
	public Object[][] getSearchKeywordsTestData() {
		return ExcelUtil.getTestData("OpenCart_SearchKeywords");
	}

	@Test(dataProvider = "getSearchKeywordsTestData")
	public void searchTestData(String searchForText) throws InterruptedException {
		driver.findElement(searchField).clear();
		driver.findElement(searchField).sendKeys(searchForText);
		driver.findElement(searchField).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
	}
}
