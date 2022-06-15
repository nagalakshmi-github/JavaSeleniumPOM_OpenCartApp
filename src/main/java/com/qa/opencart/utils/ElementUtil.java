package com.qa.opencart.utils;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {
	// SRP: Single Responsibility Pattern : Every class is reponsible for its own
	// functionality and
	// there should be only be one reason to change the code in this class
	public WebDriver driver;
	public JavaScriptExecutorUtil jseUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jseUtil=new JavaScriptExecutorUtil(this.driver);
	}

	public WebElement getElement(By locator) {
		WebElement element=driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jseUtil.flash(element);
		}
		return element;
	}

	public List<WebElement> doGetElements(By locator) {
		return driver.findElements(locator);
	}

	public void doSendKeys(By locator, String value) {
		WebElement element=getElement(locator);
		element.clear();
		element.sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public List<String> getAttributeList(By locator, String attributeName) {
		List<WebElement> eleList = doGetElements(locator);
		List<String> attrList = new ArrayList<String>();

		for (WebElement e : eleList) {
			String attrVal = e.getAttribute(attributeName);
			attrList.add(attrVal);
		}
		return attrList;
	}

	public List<String> getLinksTextList(By locator) {
		List<WebElement> list = doGetElements(locator);
		List<String> textList = new ArrayList<String>();

		for (WebElement e : list) {
			String text = e.getText();
			if (!text.isEmpty()) {
				textList.add(text);
			}
		}
		return textList;
	}

	public int getLinksCount(By locator) {
		return getLinksTextList(locator).size();
	}

	public List<WebElement> getLinksList(By locator) {
		return doGetElements(locator).stream().filter(e -> !e.getText().isEmpty()).collect(Collectors.toList());
	}

	/*
	 * if we pass wrong locator value to findElements(); No exception will be
	 * thrown; instead list returned by findElements() = 0;
	 */
	public void verifyElementsPresent(By locator) {
		List<WebElement> emailList = doGetElements(locator);
		System.out.println(emailList.size());

		try {
			if (emailList.size() == 0) {
				System.out.println("element not present");
				throw new Exception("ElementNotPresentException");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*******************
	 * "Select class based Drop Downs with <Select> tag" Utils
	 **************************/
	public void doSelectDropDownByIndex(By locator, int index) {
		Select sel = new Select(getElement(locator));
		sel.selectByIndex(index);
	}

	public void doSelectDropDownByValue(By locator, String value) {
		Select sel = new Select(getElement(locator));
		sel.selectByValue(value);
	}

	public void doSelectDropDownByVisibleText(By locator, String text) {
		Select sel = new Select(getElement(locator));
		sel.selectByVisibleText(text);
	}

	/*
	 * generic function using getOptions() to get text for all values in a dropdown
	 */
	public List<String> doGetAllDropDownOptions(By locator) {
		Select select = new Select(getElement(locator));

		List<WebElement> optionsList = select.getOptions();
		System.out.println(optionsList.size());

		List<String> optionsTextList = new ArrayList<String>();

		for (WebElement e : optionsList) {
			optionsTextList.add(e.getText());
		}
		return optionsTextList;
	}

	/*
	 * generic function using getOptions() for selecting dropdown value and without
	 * using selectByIndex()/value()/visibleText() methods
	 */
	public void doSelectDropDownValue(By locator, String value) {
		Select select = new Select(getElement(locator));

		List<WebElement> optionsList = select.getOptions();
		for (WebElement e : optionsList) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	/*******************
	 * "Without using Select class - Drop Downs operations" Utils
	 **************************/
	public void selectDropDownValue(By locator, String value) {
		List<WebElement> optionsList = doGetElements(locator);

		for (WebElement e : optionsList) {
			if (e.getText().contains(value)) {
				e.click();
				break;
			}
		}
	}

	/*
	 * TC 01: Single choice selection ; TC 02: Multiple choices selection ; TC 03:
	 * All choices selection with unique identifier 'automation_all'
	 */
	public void selectChoice(By locator, String... value) {
		List<WebElement> choiceList = driver.findElements(locator);

		if (!value[0].equalsIgnoreCase("automation_all")) {
			for (int i = 0; i < choiceList.size(); i++) {
				String text = choiceList.get(i).getText();
				System.out.println(text);

				for (int j = 0; j < value.length; j++) {
					if (text.equals(value[j])) {
						choiceList.get(i).click();
						break;
					}
				}
			}
		} else {
			// select all the choices
			// choiceList.stream().filter(t -> t.isDisplayed()).forEach(e-> e.click());
			try {
				for (WebElement e : choiceList) {
					e.click();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("All displayed choices in the jquery dropdown are selected....");
			}
		}
	}

	/*---------------------- Wait utils for webelements----------------------------*/
	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement doPresenceOfElementLocated(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement doPresenceOfElementLocated(By locator, int timeOut, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut, intervalTime);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that an element, known to be present on the DOM
	 * of a page, is visible. Visibility means that the element is not only
	 * displayed but also has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement isElementVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}

	/*---------------------- Wait utils for non-webelements----------------------------*/
	/*---------------------- Wait utils for Alert--------------------*/
	/*
	 * made this method private to be accessed by public methods to acheive
	 * encapsulation
	 */
	private Alert waitForAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(int timeOut) {
		waitForAlert(timeOut).accept();
	}

	public void dismissAlert(int timeOut) {
		waitForAlert(timeOut).dismiss();
	}

	public String alertGetText(int timeOut) {
		return waitForAlert(timeOut).getText();
	}

	public void alertSendKeys(int timeOut, String value) {
		waitForAlert(timeOut).sendKeys(value);
	}

	/*---------------------- Wait utils for Frame--------------------*/
	public void waitForFrameAndSwitch(String nameOrId, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
	}

	public void waitForFrameAndSwitch(By frameLocator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	public void waitForFrameAndSwitch(int frameIndex, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public void waitForFrameAndSwitch(WebElement frameElement, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	/*---------------------- Wait utils for URL--------------------*/
	public boolean waitForUrlContains(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.urlContains(urlFraction));
	}

	public boolean waitForUrlToBe(String url, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.urlToBe(url));
	}

	/*---------------------- Wait utils for Title--------------------*/
	public String waitForTitleIs(String expTitle, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		if (wait.until(ExpectedConditions.titleIs(expTitle)))
			return driver.getTitle();
		return null;
	}

	public String waitForTitleContains(String titleFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		if (wait.until(ExpectedConditions.titleContains(titleFraction)))
			return driver.getTitle();
		return null;
	}

	/*---------------------- Wait utils for Visibility/invisibility of list of elements--------------------*/
	public List<WebElement> waitForElementsToBeVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public Boolean waitForElementsToBeInVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void printTextOfAllVisibleElementsWithWait(By locator, int timeOut) {
		waitForElementsToBeVisible(locator, timeOut).stream().forEach(e -> System.out.println(e.getText()));
	}

	public List<String> getElementsTxtListWithWait(By locator, int timeOut) {
		List<String> footerLinksTxtList = new ArrayList<String>();
		for (WebElement e : waitForElementsToBeVisible(locator, timeOut)) {
			footerLinksTxtList.add(e.getText().trim());
		}
		return footerLinksTxtList;
	}


	/*---------------------- Wait utils to click on a particular webelement when ready--------------------*/
	public void clickWhenReadyUsingBy(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public void clickWhenReadyUsingWE(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(getElement(locator))).click();
	}

	/*---------------------- Fluent Wait utils for WE, Frame, Alert ------------------------------*/
	public WebElement waitForElementTobePresentWithFluentWait(By locator, int timeOut, long pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.withMessage(Error.TIME_OUT_WEB_ELEMENT_MSG).pollingEvery(Duration.ofMillis(pollingTime))
				.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebDriver waitForFrameWithFluentWait(By frameLocator, int timeOut, long pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.withMessage(Error.TIME_OUT_FRAME_ELEMENT_MSG).pollingEvery(Duration.ofMillis(pollingTime))
				.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	public Alert waitForAlertWithFluentWait(int timeOut, long pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.withMessage(Error.TIME_OUT_ALERT_MSG).pollingEvery(Duration.ofMillis(pollingTime))
				.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

		return wait.until(ExpectedConditions.alertIsPresent());
	}

	/********* Actions class utils ****************/
	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}

	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}

}
