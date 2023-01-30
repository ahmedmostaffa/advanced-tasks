package listeners;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import utilities.images.Screenshots;

public class ReportManager {

    public static void log(String message){
        Reporter.log(message);

    }

    public static void attachImageToAllure(WebDriver driver){
        screenshot(driver);
    }

    @Step("{message}")
    private static void logStep(String message) {

    }

    @Attachment(value = "Screenshot", type = "image/png")
    private static byte[] screenshot(WebDriver driver) {
        return Screenshots.takeScreenshot(driver);
    }
public enum Mark {
        INFO,ERROR,PASS
    }
}
