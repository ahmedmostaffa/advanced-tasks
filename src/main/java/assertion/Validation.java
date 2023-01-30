package assertion;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.ComparisonFailure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.browser.BrowserActions;
import utilities.element.ElementActions;
import utilities.element.WebElementWait;
import utilities.images.Screenshots;

public class Validation {
	// static instance of class
	private static Validation validationObject;
	private static Logger log = LogManager.getLogger();
	private WebDriver driver;

	/**
	 *
	 * @param driver WebDriver
	 */

	private Validation(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 *
	 * @param driver WebDriver
	 * @return Validation Object used to initialize Validation object
	 */
	// singleton design pattern  used for instantiating object to be access
	
	public static Validation using(WebDriver driver) {
		validationObject = new Validation(driver);
		return validationObject;
	}

	/**
	 *
	 * @param locator By
	 * @param expectedText String
	 * @return Validation Object
 	 */
	
	// this method will check/assert inner text of web element with expected text  
	public Validation verifyText(By locator, String expectedText) {
			assertThat(WebElementWait.waitUntilElementBeVisible(driver,locator).getText())
					.isNotEmpty()
					.isEqualTo(expectedText);
			return this;

	}

	/**
	 *
	 * @param locator
	 * @return
	 */

	// this method will check/assert visibility of web element at the web page 
	public Validation shouldBeVisible(By locator) {
		assertThat(WebElementWait.waitUntilElementBeVisible(driver,locator).isDisplayed())
				.isTrue();
		return this;
	}

	/**
	 *
	 * @param locator
	 * @return
	 */

	// this method will check/assert status of web element such as (radio button) to be checked or selected.
	public Validation shouldBeSelected(By locator) {
		assertThat(WebElementWait.waitUntilElementBeVisible(driver,locator).isSelected())
				.isTrue();
		return this;
	}

	/**
	 *
	 * @param locator
	 * @return
	 */
	
	// this method will check/assert web element to be clickable or intractable
	public Validation shouldBeClickable(By locator) {
		assertThat(WebElementWait.waitUntilElementBeVisible(driver,locator).isEnabled())
				.isTrue();
		return this;
	}

	/**
	 *
	 * @param locator
	 * @param text
	 * @return
	 */
	// this method will check/assert inner text of web element to contain  a certain pattern 
	public Validation shouldContain(By locator, String text) {
				assertThat(WebElementWait.waitUntilElementBeVisible(driver,locator).getText())
					.contains(text);
			return this;
	}

	/**
	 *
	 * @param locator
	 * @param attribute
	 * @return
	 */
	
	// this method will check/assert web element must have a specified attribute
	public Validation shouldHaveAttribute(By locator, String attribute) {
		String attr=WebElementWait.waitUntilElementBeVisible(driver,locator).getAttribute(attribute);
		assertThat(attr).isNotEmpty();
		return this;
	}

	/**
	 *
	 * @param locator By
	 * @param attribute String
	 * @param value String
	 * @return
	 */
	
	// this method will check/assert web element attribute must have a specified value
	public Validation shouldHaveAttribute(By locator, String attribute,String value) {
		String attr=WebElementWait.waitUntilElementBeVisible(driver,locator).getAttribute(attribute);
		assertThat(attr).isNotEmpty().isEqualTo(value);
		return this;
	}

	/**
	 *
	 * @param expectedTitle String
	 * @return
	 */
	public Validation shouldHaveTitle(String expectedTitle){
		assertThat(BrowserActions.getPageTitle(driver))
				.isNotEmpty()
				.isEqualTo(expectedTitle);
		return this;
	}

	/**
	 *
	 * @param name
	 * @param value
	 * @param locator
	 * @return
	 */
	public Validation toHaveCSS(String name,String value,By locator){
			String actualValue=ElementActions.getCssProperty(driver,locator,name);
			assertThat(value).isNotEmpty()
					.isEqualTo(actualValue);
			return this;
	}

	/**
	 *
	 * @param locator
	 * @param text
	 * @return
	 */

	public Validation notContain(By locator,String text){
		String actualText=WebElementWait.waitUntilElementBeVisible(driver,locator).getText();
			assertThat(actualText)
					.doesNotContain(text);
			return this;

	}



	/**
	 *
	 * @param locator
	 * @param length
	 * @return
	 */
	public Validation hasLength(By locator,int length){
		assertThat(WebElementWait.waitListOfWebElementVisible(driver,locator))
				.isNotEmpty()
				.hasSize(length);
		return this;
	}


	/**
	 *
	 * @param locator
	 * @return
	 */
	public Validation hover(By locator){
		ElementActions.hover(driver,locator);
		return this;
	}

	/**
	 *
	 * @param locator
	 * @param text
	 * @return
	 */
	public Validation verifyThat(By locator, String text) {
		assertThat(WebElementWait.waitUntilElementBeVisible(driver, locator).getText()).
				isNotEmpty()
				.isEqualTo(text);
		return this.hover(locator);
	}

	/**
	 *
	 * @param text
	 * @param locator
	 * @return
	 */
	public Validation startWith(By locator,String text){
			assertThat(WebElementWait.waitUntilElementBeVisible(driver,locator).getText())
					.isNotEmpty()
					.startsWith(text);
			return this;

	}

	/**
	 *
	 * @param title
	 * @return
	 */
	public Validation pageContainTitle(String title){
		assertThat(driver.getTitle())
				.isNotEmpty()
				.contains(title);
		return this;
	}

	



}
