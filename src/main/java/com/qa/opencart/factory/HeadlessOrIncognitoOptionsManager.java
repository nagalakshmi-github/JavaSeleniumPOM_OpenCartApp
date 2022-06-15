package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;


/**
 * This method is used to maintain all the browser specific options. 
 *
 */
public class HeadlessOrIncognitoOptionsManager {
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;

	public HeadlessOrIncognitoOptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		System.out.println("chrome options headless :"+prop.getProperty("headless"));
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			co.addArguments("--headless");
		}

		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
			co.addArguments("--incognito");
		}

		return co;
	}

	public FirefoxOptions getFireFoxOptions() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			fo.addArguments("--headless");
		}

		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
			fo.addArguments("--incognito");
		}

		return fo;
	}
}
