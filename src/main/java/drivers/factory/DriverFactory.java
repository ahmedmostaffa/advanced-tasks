package drivers.factory;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import utilities.dataHelpers.ConfigReader;

	
public class DriverFactory extends IFactory {
	private static Logger log= LogManager.getLogger();

	@Override
	public WebDriver createWebDriver(String browser) {
		var mode=ConfigReader.getStringProperty("RunMode");
		RunModes runMode=RunModes.valueOf(mode.toUpperCase());
		WebDriver driver;
		switch(runMode) {
			case LOCAL:
				driver =new LocalDriverFactory().createWebDriver(browser);
				break;
			case REMOTE:
				driver= new RemoteDriverFactory().createWebDriver(browser);
				break;
			default:
				log.error("no supported run mode");
				throw new IllegalStateException("invalid run mode entered");
		}
		return driver;
	}



	

enum RunModes{
	REMOTE,LOCAL;
}

}


