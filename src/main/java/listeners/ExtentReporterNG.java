package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;
import org.testng.xml.XmlSuite;
import tech.grasshopper.reporter.ExtentPDFReporter;
import utilities.dataHelpers.ConfigReader;


import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.*;

public class ExtentReporterNG implements IReporter {
	private static Logger log= LogManager.getLogger();
	private static final String OUTPUT_FOLDER = readData("extent.reporter");
	private static final String SPARK_NAME = readData("extent.reporter.spark");
	private static final String PDF_NAME = readData("extent.reporter.pdfReport");
	private static final String HTML_NAME = readData("extent.reporter.html");

	private static final String PROJECT_PATH=System.getProperty("user.dir");
	private ExtentReports extent;

	@SneakyThrows
	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		try {
			init();
			log.info("Generating extent report successfully");
		} catch (Exception e) {
			log.error("failed to generate extent report");
			e.printStackTrace();
		}

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();
			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();
				buildTestNodes(context.getFailedTests(), Status.FAIL);
				buildTestNodes(context.getSkippedTests(), Status.SKIP);
				buildTestNodes(context.getPassedTests(), Status.PASS);
			}
		}

		for (String s : Reporter.getOutput()) {
			extent.addTestRunnerOutput(s);


		}
		extent.flush();
		if (ConfigReader.getBooleanProperty("src/main/resources/reporting.properties","extent.open")) {
			try {
				Desktop.getDesktop().browse(new File(OUTPUT_FOLDER + SPARK_NAME).toURI());
			} catch (Throwable throwable) {
				log.error("failed action! to open extent-report html on the browser");
			}
		}

	}

	private ExtentReports init() throws Exception {
		ExtentSparkReporter spark = new ExtentSparkReporter(OUTPUT_FOLDER +SPARK_NAME);
		ExtentPDFReporter pdfReporter=new ExtentPDFReporter(OUTPUT_FOLDER+PDF_NAME);
		ExtentHtmlReporter htmlReporter =new ExtentHtmlReporter(OUTPUT_FOLDER+HTML_NAME);
		extent = new ExtentReports();
		pdfReporter.loadJSONConfig(new File(readData("extent.reporter.pdf.config")));
		htmlReporter.loadXMLConfig(readData("extent.reporter.html.config"));

		if((Boolean.parseBoolean(loadFile().getProperty("extent.reporter.pdf")))){
			extent.attachReporter(spark,pdfReporter,htmlReporter);
		}else {
			extent.attachReporter(spark,htmlReporter);
		}

		spark.config().setReportName(ConfigReader.getStringProperty("baseURL"));
		spark.loadXMLConfig(readData("extent.reporter.spark.config"));
		extent.setReportUsesManualConfiguration(true);
		extent.setSystemInfo("OS", System.getProperty("os.name"));
	    extent.setSystemInfo("RunMode",ConfigReader.getStringProperty("RunMode"));
	    Properties prop = loadFile();
		Set<Object> set= prop.keySet();
		for (Object key:set){
			if(key.toString().startsWith("systeminfo")){
				extent.setSystemInfo(key.toString().split("[.]")[1],prop.getProperty(key.toString()));
			}
		}
		return extent;


		}

	private void buildTestNodes(IResultMap tests, Status status) {
		ExtentTest test;
		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.createTest(result.getMethod().getMethodName());
				test.assignAuthor(readData("extent.author"));
				test.assignDevice(ConfigReader.getStringProperty("AUT"));
				test.assignCategory(result.getTestClass().getName());
				test.info(result.getMethod().getDescription());
				for(String message:Reporter.getOutput(result)){

					if (message.contains("<")){
						Markup markup= MarkupHelper.createCodeBlock(message, CodeLanguage.XML);
						test.info(markup);
					}
					else {
					test.log(Status.INFO, message);
					}
				}
				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);
				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable());
					if(new File("extent-reports/"+result.getMethod().getMethodName()+".png").exists()){
						test.fail(
								MediaEntityBuilder
										.createScreenCaptureFromPath(readData("screenshot.rel.path")+result.getMethod().getMethodName()+".png").build());
					}
				} else {
					test.log(status, "Test " + status.toString().toLowerCase() + "ed");
				}
				test.getModel().setStartTime(getTime(result.getStartMillis()));
				test.getModel().setEndTime(getTime(result.getEndMillis()));
			}
		}
	}
	private static Properties loadFile() throws Exception {
		try {
			Properties prop = new Properties();
			InputStream input = new FileInputStream("./src/main/resources/extent.properties");
			prop.load(input);
			return prop;
		}catch (Exception e){
			e.printStackTrace();
		}
		throw new Exception("failed to load extent.properties file");
	}
	@SneakyThrows
	public static String readData(String key){
		try {
			Properties properties=loadFile();
			String value = properties.getProperty(key);
			return value;
		}catch (Exception e){
			e.printStackTrace();
		}
		throw new Exception("failed to read data from extent.properties");
	}
	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
	public static void cleanExtentReports(){
		try {
			File directory = new File(OUTPUT_FOLDER);
			FileUtils.cleanDirectory(directory);
		}catch (Throwable throwable){
			log.error("failed action! delete extent-reports before execution");
		}
	}

}
