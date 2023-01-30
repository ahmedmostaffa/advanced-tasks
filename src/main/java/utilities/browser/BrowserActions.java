package utilities.browser;

import java.util.Set;

import exceptions.BrowserActionsException;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

import utilities.element.WebElementWait;

public class BrowserActions {
	private static Logger log = LogManager.getLogger();

	@SneakyThrows
	public static String getCurrentURL(WebDriver driver) {
		JavaScriptWait.waitForLazyLoading(driver);
		try {
			var currentURL = driver.getCurrentUrl();
			return currentURL;
		} catch (Exception e) {
			log.error("failed to print current URL");
			e.fillInStackTrace();
			throw new BrowserActionsException("failed to print page URL");
		}

	}

	@SneakyThrows
	public static String getPageTitle(WebDriver driver) {
		JavaScriptWait.waitForLazyLoading(driver);
		try {
			var pageTitle = driver.getTitle();
			return pageTitle;
		} catch (Exception e) {
			log.error("failed to print page title");
			e.fillInStackTrace();
			throw new BrowserActionsException("failed to print page title");
		}

	}

	@SneakyThrows
	public static void navigateBack(WebDriver driver) {
		JavaScriptWait.waitForLazyLoading(driver);
		try {
			driver.navigate().back();
			log.info("you have navigated back");
		} catch (Exception e) {
			log.error("failed to do navigation back");
			e.fillInStackTrace();
			e.getCause();
		}
	}

	public static void navigateForward(WebDriver driver) {
		JavaScriptWait.waitForLazyLoading(driver);
		try {
			driver.navigate().forward();
			log.info("you have navigated forward");
		} catch (Exception e) {
			log.error("failed to do navigation forward");
			e.fillInStackTrace();
			e.getCause();
		}
	}

	public static void refreshPage(WebDriver driver) {
		JavaScriptWait.waitForLazyLoading(driver);
		driver.navigate().refresh();
	}

	public static void setWindowSize(WebDriver driver, int height, int width) {
		JavaScriptWait.waitForLazyLoading(driver);
		driver.manage().window().setSize(new Dimension(height, width));
	}

	@SneakyThrows
	public static String getWidowHandle(WebDriver driver) {
		JavaScriptWait.waitForLazyLoading(driver);
		var windowHandle = " ";
		try {
			windowHandle = driver.getWindowHandle();
			return windowHandle;
		} catch (Exception e) {
			log.error("failed to get current window");
			e.fillInStackTrace();
			e.getCause();
		}
		throw new BrowserActionsException("failed to get current window");
	}

	@SneakyThrows
	public static Set<String> getMultipleWindow(WebDriver driver) {
		JavaScriptWait.waitForLazyLoading(driver);
		Set<String> windows = null;
		try {
			windows = driver.getWindowHandles();
			return windows;
		} catch (Exception e) {
			log.error("failed to handle multiple window");
			e.fillInStackTrace();
			e.getCause();
		}
		throw new BrowserActionsException("failed to print all the opened windows");
	}

	public static void switchToNewTab(WebDriver driver, String URL) {
		try {
			var handleWindowBefore = driver.getWindowHandle();
			driver.switchTo().newWindow(WindowType.TAB).navigate().to(URL);
			var handleWindowAfter = driver.getWindowHandle();
			if (!handleWindowAfter.equalsIgnoreCase(handleWindowBefore)) {
				log.info("you have successfully switched to the new tab");
			} else {
				log.error("failed to switch to new window");
			}
		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	public static void acceptAlert(WebDriver driver) {
		WebElementWait.waitUntilAlertIsPresent(driver);
		try {
			driver.switchTo().alert().accept();
		}catch (Exception e) {
			e.fillInStackTrace();
		}
	}
	
	@SneakyThrows
	public static String getAlertText(WebDriver driver) {
		WebElementWait.waitUntilAlertIsPresent(driver);
		try {
			return driver.switchTo().alert().getText();
			
		}catch (Exception e) {
			log.error("failed to print the current alert text");
			e.fillInStackTrace();
		}
		throw new BrowserActionsException("failed to print the text of alert");
	}
	
	public static void closeAlert(WebDriver driver) {
		WebElementWait.waitUntilAlertIsPresent(driver);
		try {
			driver.switchTo().alert().dismiss();
		}catch(Exception e) {
			log.error("failed to close the current alert");
			e.fillInStackTrace();
			e.getCause();
		}
	}
	
	public static void sendTextToAlert(WebDriver driver,String Text) {
		WebElementWait.waitUntilAlertIsPresent(driver);
		driver.switchTo().alert().sendKeys(Text);
	}
		
	
	
}
