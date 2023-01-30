package drivers.managers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.dataHelpers.ConfigReader;

import java.util.HashMap;
import java.util.Map;

public class ChromeDriverManager implements IDriverManager {

	@Override
	public ChromeDriver createInstanceOfDriver() {
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver(getOptions());
	}

	public ChromeOptions getOptions() {
		 Map<String, Object> chromePrefs = new HashMap<String, Object>();
		 ChromeOptions chromeOptions = new ChromeOptions();
		 chromeOptions.addArguments("--window-size=1920,1080");
		 chromeOptions.addArguments("--disable-infobars");
		 chromeOptions.addArguments("--disable-notifications");
		 chromeOptions.setHeadless(ConfigReader.getBooleanProperty("headless"));
		 chromePrefs.put("intl.accept_languages",ConfigReader.getStringProperty("Language").toLowerCase());
		 chromeOptions.setExperimentalOption("prefs", chromePrefs);
		 return chromeOptions;

	}

}
