package utilities.images;

import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import utilities.element.ElementActions;
import utilities.element.WebElementWait;

public class Screenshots {

	public Screenshots() {
		throw new IllegalStateException("it is utility class");
	}
	private static Logger log= LogManager.getLogger();

	/*
	@param driver WebDriver
	@param name the desired name of the image
	@param title printing some text inside the image
	 */
	public static void takeScreenshot(WebDriver driver, String name, String title) {
		Shutterbug.shootPage(driver).withTitle(title).withName(name + " " + getDate())
				.save(".\\resources\\screenshots");
	}

	/**
	 *
	 * @param driver WebDriver
	 * @param name String
	 */
	public static void takeScreenshot(WebDriver driver, String name) {
		Shutterbug.shootPage(driver)
				.withName(name + " " + getDate())
				.save(".\\resources\\screenshots");
	}

	/**
	 *
	 * @param driver WebDriver
	 * @param name String
	 */
	
	public static void captureFullPage(WebDriver driver, String name) {
		Shutterbug.shootPage(driver,Capture.FULL)
				.withName(name)
				.save(".\\resources\\screenshots");
	}

	/**
	 *
	 * @param driver WebDriver
	 * @param element WebElement
	 */
	public static void captureWebElement(WebDriver driver, By locator) {
		WebElement element=WebElementWait.waitUntilElementBeVisible(driver,locator);
		Shutterbug
				.shootElement(driver,element)
				.withName(element.getText() + " " + getDate())
				.save(".\\resources\\screenshots");
	}

	/**
	 *
	 * @param driver
	 * @param locator
	 * @param name String name of the saved image
	 */
	public static void captureWebElement(WebDriver driver,By locator,String name) {
		WebElement element=WebElementWait.waitUntilElementBeVisible(driver,locator);
		Shutterbug
				.shootElement(driver,element)
				.withName(name)
				.save(".\\resources\\screenshots");
	}

	/**
	 *
	 * @param driver WebDriver
	 * @param locator By (e.g. By pageHeader = By.cssSelector("span.title"); )
	 */
	public static void highlightBlur(WebDriver driver , By locator,String text) {
		WebElement element=WebElementWait.waitUntilElementBeVisible(driver,locator);
		Shutterbug.shootPage(driver)
				.highlightWithText(element,Color.RED,8
						,text
						,Color.yellow,new Font("SansSerif", Font.BOLD, 21))
				.blurExcept(element)
				.save(".\\resources\\screenshots\\bugs");
	}

	/**
	 *
	 * @param driver WebDriver
	 * @param locator
	 */
	public static void focusOnWebElement(WebDriver driver,By locator,String text){
		WebElement element=WebElementWait.waitUntilElementBeVisible(driver,locator);
		Shutterbug
				.shootPage(driver)
				.highlightWithText(element,Color.RED,8
						,text
						,Color.yellow,new Font("SansSerif", Font.BOLD, 21))
				.save(".\\resources\\screenshots\\bugs");

	}

	/**
	 *
	 * @param driver WebDriver
	 * @param locator By
	 * @param text String
	 */

	public static void focusWithBlur(WebDriver driver,By locator,String text){
		WebElement element=WebElementWait.waitUntilElementBeVisible(driver,locator);
		Shutterbug
				.shootPage(driver,Capture.FULL)
				.blurExcept(element)
				.highlightWithText(element,Color.RED,5
						,text
						,Color.yellow,new Font("SansSerif", Font.BOLD, 24))
				.save(".\\resources\\screenshots\\bugs");
	}


	/**
	 *
	 * @param driver
	 * @param fileName
	 */
	public static void takeMobileScreenshot(WebDriver driver,String fileName) {
		try {
			File imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(imageFile, new File(".\\resources\\screenshots\\"+ fileName+" "+getDate()+".png"));
		}catch (Throwable throwable){
			log.error("failed to take screenshot for the mobile screen");
		}

	}

	/**
	 *
	 * @param driver
	 * @return
	 */
	public static byte[] takeScreenshot(WebDriver driver){
		TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
		return takesScreenshot.getScreenshotAs(OutputType.BYTES);
	}
	public static String getBase64Screenshot(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}

	/**
	 *
	 * @return formatDate Date get current date in 'dd-MM-yyyy HH:mm:ss' format
	 */
	private static String getDate() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String formatDate = formatter.format(date)
				.replace(" ", "-")
				.replace(":", "-");
		return formatDate;
	}
}
