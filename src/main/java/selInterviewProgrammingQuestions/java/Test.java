package selInterviewProgrammingQuestions.java;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com");
		List<WebElement> langList = driver.findElements(By.cssSelector("div#SIvCob>a"));
		for (WebElement e : langList) {
			System.out.println(e.getText());
			if (e.getText().equals("తెలుగు")) {
				e.click();
				break;
			}
		}
	}
}
