package utilities.element;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.images.Screenshots;

public class ElementActions {
	private static Logger log= LogManager.getLogger();
	
	private ElementActions() {

	}

	/**
	 *
	 * @param driver
	 * @param locator
	 */
	
	public static void click(WebDriver driver,By locator){
		WebElement element=WebElementWait.waitUntilElementBeClickable(driver,locator);
		element.click();
	}

	/**
	 *
	 * @param driver
	 * @param locator
	 * @param text
	 */
	
	public static void type(WebDriver driver , By locator,String text) {
		WebElementWait.waitUntilElementBeVisible(driver, locator);
		try {
			driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys(text);
		}catch (Throwable throwable){
			JavaScriptManager.setValue(driver,text,locator);
			log.info("passed action! the input field has been set using JavaScript execution");
		}

	}

	/**
	 *
	 * @param driver
	 * @param locator
	 * @return
	 */
	
	public static List<String> getTextOfEveryElement(WebDriver driver,By locator){
		List<WebElement> elements=WebElementWait.waitListOfWebElementVisible(driver, locator);
		List<String> result=new ArrayList<>();
		elements.forEach((WebElement elem)->{
			result.add(elem.getText());
		});
		return result;
	}

	/**
	 *
	 * @param driver
	 * @param locator
	 * @param propertyName
	 * @return
	 */
	public static String getCssProperty(WebDriver driver,By locator,String propertyName){
		return WebElementWait.waitUntilElementBeVisible(driver,locator).getCssValue(propertyName);
	}

	/**
	 *
	 * @param driver
	 * @param locator
	 */
	

	public static void hoverAndClick(WebDriver driver,By locator) {
		WebElement element=WebElementWait.waitUntilElementBeClickable(driver, locator);
		try {
			Actions actions=new Actions(driver);
			actions.moveToElement(element).click().build().perform();
		}catch (Exception rootCause){
			log.error("failed action to do a hover and click");
		}
	}

	/**
	 *
	 * @param driver
	 * @param locator
	 */

	public static void hover(WebDriver driver,By locator){
		WebElement element=WebElementWait.waitUntilElementBeClickable(driver, locator);
		try {
			JavaScriptManager.scrollToElement(driver,locator);
			Actions actions=new Actions(driver);
			actions.moveToElement(element).build().perform();
		}catch (Exception rootCause){
			rootCause.getCause();
			log.error("failed action to do a hover for the WebElement");
		}

	}

	/**
	 *
	 * @param driver
	 * @param locator
	 */
	public static void doRightClick(WebDriver driver,By locator){
		WebElement element=WebElementWait.waitUntilElementBeClickable(driver, locator);
		try {
			Actions actions=new Actions(driver);
			actions.moveToElement(element)
					.contextClick().build().perform();
		}catch (Exception throwable){
			log.error("failed action to do a right click");
		}

	}

	/**
	 *
	 * @param driver
	 * @param locator
	 */
	public static void doClick(WebDriver driver,By locator){
		if(WebElementWait.isPresent(driver,locator)) {
			try {
				// wait until the element be clickable
				click(driver, locator);
				log.info("passed! click using basic command");
			} catch (Throwable throwable) {
				log.info("the WebElement is not clickable");
				try {
					hoverAndClick(driver, locator);
					log.info("passed! click using actions");
				} catch (Throwable throwable1) {
					log.info("failed action!");
					try {
						JavaScriptManager.click(driver, locator);
						log.info("passed! click using javascript execution");
						Screenshots.takeScreenshot(driver, "passed! click using javascript execution");
					} catch (Throwable throwable2) {
						log.error("failed action! element is not clickable");
						throw throwable2;
					}
				}

			}
		}else {
			log.error("WebElement is not clickable");
		}







	}

	/**
	 *
	 * @param driver
	 * @param locator
	 * @param value
	 */


	public static void selectByValue(WebDriver driver,By locator,String value){
		WebElement element=WebElementWait.waitUntilElementBeVisible(driver,locator);
		try {

			Select select=new Select(element);
			select.selectByValue(value);
		}catch (Exception throwable){
			log.error("failed action! failed to do a selection from drop down by value");
		}

	}


	/**
	 *
	 * @param driver
	 * @param locator
	 * @param text
	 */
	public static void selectByVisibleText(WebDriver driver,By locator,String text){
		WebElement dropDown=WebElementWait.waitUntilElementBeVisible(driver,locator);
		try {
			Select select=new Select(dropDown);
			select.selectByVisibleText(text);
		}catch (Exception e){
			log.error("failed action! to do a selection from drop down by visible text");
		}
	}

	/**
	 *
	 * @param driver
	 * @param locator
	 * @return
	 */
	public static WebElement getFirstOption(WebDriver driver,By locator){
		WebElement dropDown=WebElementWait.waitUntilElementBeVisible(driver,locator);
		try {

			Select select=new Select(dropDown);
			return select.getFirstSelectedOption();
		}catch (Exception e){
			log.error("Web Element is not found/visible");
		}
		throw new IllegalStateException("failed to get the default value of the drop-down list");
	}

	/**
	 *
	 * @param driver
	 * @param locator
	 * @param text
	 */
	public static void deSelectByVisibleText(WebDriver driver,By locator,String text){
		WebElement dropDown=WebElementWait.waitUntilElementBeVisible(driver,locator);
		try {

			Select select=new Select(dropDown);
			select.deselectByVisibleText(text);
		}catch (Exception e){
			log.error("failed action! to deselect from drop down by visible text");
		}
	}

	/**
	 *
	 * @param driver
	 * @param locator
	 * @return
	 */
	public static List<WebElement> getOptions(WebDriver driver,By locator){
		WebElement dropDown=WebElementWait.waitUntilElementBeVisible(driver,locator);
		try {
			Select select=new Select(dropDown);
			return select.getOptions();
		}catch (Exception e){
			log.error("failed action! to retrieve the multiple options of drop-down list");
		}
		throw new IllegalStateException("failed to retrieve the available options from drop-down list ");
	}

	/**
	 *
	 * @param driver
	 * @param locator
	 */
	public  static void doubleClick(WebDriver driver,By locator){
		WebElement element=WebElementWait.waitUntilElementBeClickable(driver,locator);
		try {

			Actions actions=new Actions(driver);

			actions.moveToElement(element)
					.doubleClick()
					.build()
					.perform();

		}catch (Exception e){
			log.error("failed action! to do a double click ");
		}


	}


	/**
	 *
	 * @param driver
	 * @param source
	 * @param target
	 */
	public static void dragANDdrop(WebDriver driver,By source,By target){
		WebElement sourceElement=WebElementWait.waitUntilElementBeVisible(driver,source);
		WebElement targetElement=WebElementWait.waitUntilElementBeVisible(driver,target);
		Actions actions=new Actions(driver);
		try {
			actions
					.dragAndDrop(sourceElement,targetElement).build().perform();
		}catch (Throwable throwable){
			actions.moveToElement(sourceElement).clickAndHold(targetElement).build().perform();
		}
	}

	/**
	 *
	 * @param driver
	 * @param locator
	 */
	public static void selectFrom(WebDriver driver,By locator){
		if(WebElementWait.isPresent(driver,locator)){
			try {
				new Actions(driver)
						.moveToElement(WebElementWait.waitUntilElementBeVisible(driver,locator))
						.contextClick()
						.sendKeys(Keys.ARROW_DOWN).build().perform();
			}catch (Exception e){
				log.error("failed action! to select option from right click menu");
			}
		}

	}







	
	
	
	
}
