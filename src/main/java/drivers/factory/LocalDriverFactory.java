package drivers.factory;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import drivers.managers.ChromeDriverManager;
import drivers.managers.EdgeDriverManager;
import drivers.managers.FirefoxDriverManager;
import utilities.dataHelpers.ConfigReader;

import java.net.MalformedURLException;
import java.net.URL;
class LocalDriverFactory extends IFactory {
	private static Logger log=LogManager.getLogger();
	@Override
	public WebDriver createWebDriver(String browser) {
		WebDriver driver;
		Browsers browserOptions = Browsers.valueOf(browser.toUpperCase());

		switch (browserOptions) {
		case CHROME:
			driver = new ChromeDriverManager().createInstanceOfDriver();
			break;
		case FIREFOX:
			driver = new FirefoxDriverManager().createInstanceOfDriver();
			break;
		case EDGE:
			driver=new EdgeDriverManager().createInstanceOfDriver();
			break;
		default:
			log.error("no supported browser");
			throw new IllegalStateException("no supported browser");
		}
		return driver;
	}
}
