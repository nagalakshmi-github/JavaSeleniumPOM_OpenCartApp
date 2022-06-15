package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	public HeadlessOrIncognitoOptionsManager optionsManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		String browserVersion=prop.getProperty("browserversion").trim();
		String url = prop.getProperty("url");
		System.out.println("browserName:" + browserName);
		optionsManager = new HeadlessOrIncognitoOptionsManager(prop);
		highlight = prop.getProperty("highlight");

		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			System.out.println("remote value:"+prop.getProperty("remote"));
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteWD("chrome",browserVersion);
			} else {
				// driver = new ChromeDriver(optionsManager.getChromeOptions());
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteWD("firefox",browserVersion);
			} else {
			// driver = new FirefoxDriver(optionsManager.getFireFoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
			}
			break;
		case "safari":
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;
		default:
			System.out.println("Please pass the right browser...:" + browserName);
			break;
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(url);

		return getDriver();
	}

	private void init_remoteWD(String browserName,String browserVersion) {
		System.out.println("Running test on grid server:"+browserName+" version: "+browserVersion );
		System.out.println("hub url:"+prop.getProperty("huburl"));
		if (browserName.equalsIgnoreCase("chrome")) {
			DesiredCapabilities cap =new DesiredCapabilities();
			cap.setCapability("browserName", "chrome");
			cap.setCapability("browserversion", browserVersion);
			cap.setCapability("enableVNC",true);
			cap.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromeOptions());
			try {
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else if (browserName.equalsIgnoreCase("firefox")) {
			DesiredCapabilities cap =new DesiredCapabilities();
			cap.setCapability("browserName", "firefox");
			cap.setCapability("browserversion", browserVersion);
			cap.setCapability("enableVNC",true);
			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsManager.getFireFoxOptions());
			try {
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFireFoxOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties init_prop() {
		FileInputStream fs = null;
		prop = new Properties();
		String envName = System.getProperty("env");
		if (envName == null) {
			System.out.println("Running on environment on PROD env");
			try {
				fs = new FileInputStream("./src/test/resources/Config/google_config.properties");
				//fs = new FileInputStream("./src/test/resources/Config/Config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				System.out.println("Running on environment:" + envName);
				switch (envName) {
				case "qa":
					fs = new FileInputStream("./src/test/resources/Config/QA.config.properties");
					break;
				case "stage":
					fs = new FileInputStream("./src/test/resources/Config/stage.config.properties");
					break;
				case "dev":
					fs = new FileInputStream("./src/test/resources/Config/dev.config.properties");
					break;
				default:
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
		try {
			prop.load(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	// Method to create screenshot for failed test cases while executing
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
