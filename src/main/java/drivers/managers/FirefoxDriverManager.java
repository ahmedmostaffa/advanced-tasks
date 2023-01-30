package drivers.managers;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxProfile;
import utilities.dataHelpers.ConfigReader;

public class FirefoxDriverManager implements IDriverManager {


	@Override
	public FirefoxDriver createInstanceOfDriver() {
		WebDriverManager.firefoxdriver().setup();
		return new FirefoxDriver(getOptions());
	}

	public FirefoxOptions getOptions() {
		 FirefoxOptions firefoxOptions = new FirefoxOptions();
		 FirefoxProfile firefoxProfile = new FirefoxProfile();
		 firefoxProfile.setPreference("intl.accept_languages",ConfigReader.getStringProperty("Language").toLowerCase());
		 firefoxOptions.setProfile(firefoxProfile);
		 firefoxOptions.addArguments("--window-size=1920,1080");
		 firefoxOptions.addArguments("--disable-infobars");
		 firefoxOptions.addArguments("--disable-notifications");
		 firefoxOptions.setHeadless(ConfigReader.getBooleanProperty("headless"));
		 return firefoxOptions;
	}
}
