package listeners;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseTest;
import utilities.images.Screenshots;


public class WebEventListener extends BaseTest implements  ITestListener{
	private static Logger log=LogManager.getLogger();
	@Override
	public void onTestStart(ITestResult result) {
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Screenshots.takeScreenshot(getDriver(),result.getMethod().getMethodName());
		log.info("take screenshot for failed tests");



	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

}
