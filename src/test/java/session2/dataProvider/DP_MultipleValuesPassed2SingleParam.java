package session2.dataProvider;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DP_MultipleValuesPassed2SingleParam {
	public class GoogleSearchKeywords {
		public WebDriver driver;
		By inputText=By.xpath("//input[@name='q']");
		
		@DataProvider(name = "Search data")
		public Object[][] dataProvFunc() {
			return new Object[][]{
              	{"Selenium","Delhi"},{"QTP","Bangalore"},{"LoadRunner","Chennai"}
        	};
		}
		
		@BeforeTest
		public void setUp() {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.get("https://www.google.com");
		}

		// Passing the dataProvider to the test method through @Test annotation
		@Test(dataProvider = "Search data")
		public void googleSearchText(String txt1, String txt2) {
			driver.findElement(inputText).clear();
			driver.findElement(inputText).sendKeys(txt1," ",txt2);
			driver.findElement(inputText).sendKeys(Keys.ENTER);
			Reporter.log("Search results are displayed...");
		}

		@AfterTest
		public void tearDown() {
			driver.quit();
		}
	}
}
