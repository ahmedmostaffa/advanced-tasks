package utilities.element;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import utilities.browser.JavaScriptWait;

public class JavaScriptManager {

	public JavaScriptManager() {
		throw new IllegalStateException("it is utility class");
	}

	/**
	 *
	 * @param driver
	 * @return
	 */
	public static String getPageInnerText(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		JavaScriptWait.waitForLazyLoading(driver);
		return js.executeScript(" return document.documentElement.innerText;").toString();
	}

	/**
	 *
	 * @param driver
	 * @return
	 */

	public static String getPageTitle(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		JavaScriptWait.waitForLazyLoading(driver);
		return js.executeScript("return document.title;").toString();
	}

	/**
	 *
	 * @param driver
	 * @return
	 */

	public static String getDomain(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		JavaScriptWait.waitForLazyLoading(driver);
		return js.executeScript("return document.domain;").toString();
	}


	public static void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		JavaScriptWait.waitForLazyLoading(driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}


	/**
	 *
	 * @param driver
	 */

	public static void scrollToTopPage(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		JavaScriptWait.waitForLazyLoading(driver);
		js.executeScript("window.scrollTo(0,0);");
	}

	/**
	 *
	 * @param driver
	 * @param offset
	 */
	public static void scrollByOffset(WebDriver driver, String offset) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		JavaScriptWait.waitForLazyLoading(driver);
		js.executeScript("window.scrollBy(0,'" + offset + "');");
	}

	/**
	 *
	 * @param driver
	 */
	public static void generateAlertPopWindow(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("alert('Generated alert pop');");
	}

	/**
	 *
	 * @param driver
	 * @param customMessage
	 */

	public static void generateAlertPopWindow(WebDriver driver, String customMessage) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("alert('" + customMessage + "');");
	}

	/**
	 *
	 * @param driver
	 * @param locator
	 */
	// you can use it to click on hidden element using javaScript in web page
	public static void click(WebDriver driver, By locator) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		JavaScriptWait.waitForLazyLoading(driver);
		js.executeScript("arguments[0].click();", WebElementWait.waitUntilElementBePresent(driver,locator));
	}

	/**
	 *
	 * @param driver
	 * @param locator
	 */

	// you can use it to show/make hidden element visible on javaScript in web page
	public static void showHiddenElement(WebDriver driver, By locator) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		JavaScriptWait.waitForLazyLoading(driver);
		js.executeScript("arguments[0].style.display='block'",
				WebElementWait.waitUntilElementBePresent(driver,locator));
	}

	/**
	 *
	 * @param driver
	 * @param locator
	 */

	// if you wanna to scroll down till element is visible/displayed
	public static void scrollToElement(WebDriver driver, By locator) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		JavaScriptWait.waitForLazyLoading(driver);
		js.executeScript("arguments[0].scrollIntoView()", WebElementWait.waitUntilElementBePresent(driver,locator));
	}

	/**
	 *
	 * @param driver
	 * @param value
	 */
	public static void setValue(WebDriver driver,String value,By locator){
		JavascriptExecutor js=(JavascriptExecutor) driver;
		JavaScriptWait.waitForLazyLoading(driver);
		JavaScriptManager.scrollToElement(driver,locator);
		js.executeScript("arguments[0].setAttribute('value','" + value + "')",WebElementWait.waitUntilElementBeVisible(driver,locator));
	}

	/**
	 *
	 * @param driver
	 * @param locator
	 */
	public static void focusAndClick(WebDriver driver,By locator){
		JavascriptExecutor js=(JavascriptExecutor) driver;
		JavaScriptWait.waitForLazyLoading(driver);
		WebElement element=WebElementWait.waitUntilElementBeClickable(driver,locator);
		js.executeScript("arguments[0].focus();",element);
		js.executeScript("arguments[0].click();",element);

	}







}
