package utilities.element;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.dataHelpers.ConfigReader;

public class WebElementWait {
	private static int MAX_TIME_OUT = ConfigReader.getIntegerProperty("max.timeOut.explicitWait");

	/**
	 *
	 * @param driver
	 * @param locator
	 * @return
	 */
	
	// wait until the DOM element to  be Clickable or Intractable
	public static WebElement waitUntilElementBeClickable(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_TIME_OUT));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 *
	 * @param driver
	 * @param locator
	 * @return
	 */

	// wait for the specified WebElement to be present on the DOM of the page.
	public static WebElement waitUntilElementBePresent(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_TIME_OUT));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 *
	 * @param driver
	 * @param locator
	 * @return
	 */
	// wait until DOM element is selected
	public static Boolean waitUntilElementBeSelected(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_TIME_OUT));
		return wait.until(ExpectedConditions.elementToBeSelected(locator));
	}

	/**
	 *
	 * @param driver
	 * @param locator
	 * @return
	 */

	// wait until DOM element is visible or displayed on web page
	public static WebElement waitUntilElementBeVisible(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_TIME_OUT));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 *
	 * @param driver
	 * @param title
	 * @return
	 */

	// wait until the page title must be an exact match the expected title
	public static Boolean waitUntilPageTitleIs(WebDriver driver, String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_TIME_OUT));
		return wait.until(ExpectedConditions.titleIs(title));
	}

	/**
	 *
	 * @param driver
	 * @param title
	 * @return
	 */

	// wait until the page title contains/match a specific title
	public static Boolean waitUntilPageTitleContains(WebDriver driver, String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_TIME_OUT));
		return wait.until(ExpectedConditions.titleContains(title));
	}

	/**
	 *
	 * @param driver
	 * @return
	 */
	
	public static Alert waitUntilAlertIsPresent(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_TIME_OUT));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	/**
	 *
	 * @param driver
	 * @param locator
	 * @return
	 */
	// wait until all the list of web elements are visible
	public static List<WebElement> waitListOfWebElementVisible(WebDriver driver,By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_TIME_OUT));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	/**
	 *
	 * @param driver
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public static WebElement waitUntilElementBeVisible(WebDriver driver, By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 *
	 * @param driver
	 * @param locator
	 * @return
	 */
	public static List<WebElement> waitListOfWebElementVisible(WebDriver driver,By locator,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	/**
	 *
	 * @param driver
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public static boolean waitUntilElementInvisible(WebDriver driver, By locator,int timeOut){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		boolean condition = wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		return condition;
	}

	/**
	 *
	 * @param driver
	 * @param locator
	 * @return
	 */

	/**
	 *
	 * @param driver
	 * @param timeOut
	 * @param pollingTime
	 * @return
	 */
	public static Wait<WebDriver> fluentWait(WebDriver driver, int timeOut, int pollingTime){
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30L))
				.pollingEvery(Duration.ofSeconds(5L))
				.ignoring(NoSuchElementException.class);
		return wait;
	}

	/**
	 * 
	 * @param driver
	 * @param timeOut
	 * @param pollingTime
	 * @param locator
	 * @return
	 */
	public static WebElement fluentWaitUntil_ElementBeVisible(WebDriver driver, int timeOut, int pollingTime, By locator){
		return fluentWait(driver,timeOut,pollingTime).until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	/**
	 *
	 * @param driver
	 * @param locator
	 * @return
	 */
	public static List<WebElement> waitUntilElementsBePresent(WebDriver driver, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_TIME_OUT));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}


	public static boolean isPresent(WebDriver driver,By locator){
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(MAX_TIME_OUT));
		boolean condition=false;
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			condition=true;
		}catch (TimeoutException timeoutException){
			condition=false;
			throw timeoutException;

		}
		return condition;
	}

}
