package drivers.factory;

import java.net.URL;

import drivers.managers.EdgeDriverManager;
import lombok.SneakyThrows;
import 	org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import drivers.managers.ChromeDriverManager;
import drivers.managers.FirefoxDriverManager;
import utilities.dataHelpers.ConfigReader;

import java.net.MalformedURLException;

class RemoteDriverFactory extends IFactory {
	static Logger log=LogManager.getLogger();

	/**
	 *
	 * @param browser String name of the browser (e.g. chrome ,firefox,edge.... )
	 * @return Remote WebDriver with the desired-capabilities
	 */
	@Override
	public WebDriver createWebDriver(String browser) {
	    MutableCapabilities capability;
	    Browsers browserOptions = Browsers.valueOf(browser.toUpperCase());
	    switch (browserOptions) {
	        case CHROME:
	            capability = new ChromeDriverManager().getOptions();
	            break;
	        case FIREFOX:
	            capability = new FirefoxDriverManager().getOptions();
	            break;
			case EDGE:
				capability=new EdgeDriverManager().getOptions();
				break;
	        default:
	            log.error("no browser supported");
	        	throw new IllegalStateException(browser);
	    }
	    return createRemoteDriver(capability);
	}

	/**
	 *
	 * @param capability
	 * @return
	 */

	private RemoteWebDriver createRemoteDriver(MutableCapabilities capability) {
		try {
			RemoteWebDriver remoteWebDriver;
            remoteWebDriver = new RemoteWebDriver(new URL(ConfigReader.getStringProperty("RemoteURL")), capability);
            return remoteWebDriver;
        } catch (MalformedURLException e) {
        	log.error("failed to instantiating the RemoteWebDriver ");
			e.fillInStackTrace();
		}
		throw new IllegalStateException("failed to instantiating the RemoteWebDriver ");
	}


}
