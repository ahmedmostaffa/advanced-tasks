package drivers.managers;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.dataHelpers.ConfigReader;

import java.util.HashMap;
import java.util.Map;

public class EdgeDriverManager implements IDriverManager {

	@Override
	public EdgeDriver createInstanceOfDriver() {
		WebDriverManager.edgedriver().setup();
		return new EdgeDriver(getOptions());
	}

	public EdgeOptions getOptions() {
		 Map<String, Object> edgePref = new HashMap<String, Object>();
		 EdgeOptions edgeOptions = new EdgeOptions();
		 edgeOptions.addArguments("--window-size=1920,1080");
		 edgeOptions.addArguments("--disable-infobars");
		 edgeOptions.addArguments("--disable-notifications");
		 edgeOptions.setHeadless(ConfigReader.getBooleanProperty("headless"));
		 edgePref.put("intl.accept_languages",ConfigReader.getStringProperty("Language").toLowerCase());
		 edgeOptions.setExperimentalOption("prefs", edgePref);
		 return edgeOptions;
	}
}
