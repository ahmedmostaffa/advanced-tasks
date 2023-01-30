package utilities.browser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JavaScriptWait {
	/**
	 * wait for lazy loading
	 */
	private static Logger log = LogManager.getLogger();

	/**
	 *
	 * @param driver
	 * wait until all the content such as (Javascript,css style sheets ,images ,AJAX calls are ready)
	 */
	public static void waitForLazyLoading(WebDriver driver) {
		boolean pageFullyLoaded = false;
		String pageStatus = null;
		while (!pageFullyLoaded) {
			pageStatus = returnPageStatus(driver);
			log.debug("page are ready");
			if ((pageStatus.equalsIgnoreCase("complete")) || (pageStatus.equalsIgnoreCase("interactive"))) {
				pageFullyLoaded = true;

			}
		}
		waitForAJAX(driver);
	}

	/**
	 *
	 * @param driver
	 */
	public static void waitForAJAX(WebDriver driver) {
		if (checkJQuery(driver).equalsIgnoreCase("function")) {
			boolean status = false;
			while (!status) {
				status=returnAJAX(driver);
				sleep();
			}
		}
	}

	/**
	 *
	 * @param driver
	 * @return
	 */

	private static boolean returnAJAX(WebDriver driver){
			try{
				JavascriptExecutor js=(JavascriptExecutor) driver;
				boolean jQueryStatus= (long) js.executeScript("return jQuery.active;")==0;
				return jQueryStatus;
			}catch (Exception e){
				// there is no ajax elements present
			}
		return false;
	}

	/**
	 *
	 * @param driver WebDriver
	 * @return condition String (undefined or function)
	 */
	private static String checkJQuery(WebDriver driver){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String condition=js.executeScript("return typeof jQuery;").toString();
		return condition;
	}

	/**
	 *
	 * @param driver WebDriver
	 * @return pageStatus String (loading, interactive ,complete)
	 */
	private static String returnPageStatus(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String pageStatus = js.executeScript("return document.readyState;").toString();
		return pageStatus;
	}
	// The duration in milliseconds to sleep between polls.
	private static void sleep(){
		try {
			Thread.sleep(50);
		}catch (InterruptedException e){

		}
	}


	public static void main(String[] args) {
	}
}
