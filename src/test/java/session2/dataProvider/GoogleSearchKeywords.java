package session2.dataProvider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GoogleSearchKeywords {
	public WebDriver driver;
	By inputText=By.xpath("//input[@name='q']");
	
	@BeforeTest
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
	}

	// Passing the dataProvider to the test method through @Test annotation
	@Test(dataProvider = "Search data", dataProviderClass = DPClass.class)
	public void googleSearchText(String text) {
		driver.findElement(inputText).clear();
		driver.findElement(inputText).sendKeys(text);
		driver.findElement(inputText).submit();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
