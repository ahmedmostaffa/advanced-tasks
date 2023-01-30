package drivers.factory;

import org.openqa.selenium.WebDriver;

abstract class IFactory {

    public abstract WebDriver createWebDriver(String browser);


}
