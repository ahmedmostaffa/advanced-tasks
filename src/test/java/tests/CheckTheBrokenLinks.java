package tests;

import base.BaseTest;
import listeners.ReportManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.browser.JavaScriptWait;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class CheckTheBrokenLinks extends BaseTest {
    @Test
    public static void validateTheLinks() throws InterruptedException {
        JavaScriptWait.waitForLazyLoading(getDriver());
        Thread.sleep(30000);

        List<WebElement> elementList=getDriver().findElements(By.xpath("//a[@href]"));
        ReportManager.log("number of the found elements are"+" "+elementList.size());
        elementList.forEach((webElement -> {
            HttpURLConnection httpURLConnection=checkLinks(webElement.getAttribute("href"));
            try {
                if (httpURLConnection.getResponseCode()>=200){
                    ReportManager.log("error"+webElement.getAttribute("href")+"----> "+httpURLConnection.getResponseCode()+" "+httpURLConnection.getResponseMessage());
                }else {
                    ReportManager.log(webElement.getAttribute("href")+"----> "+httpURLConnection.getResponseCode()+" "+httpURLConnection.getResponseMessage());
                }
            } catch (IOException e) {

            }
        }));
    }

    private static HttpURLConnection checkLinks(String link) {
        if ((link != null) && (link.contains("https"))) {
            // send HTTP Request to check the functionality of each item
            try {
                URL link_url = new URL(link);
                HttpURLConnection response = (HttpURLConnection) link_url.openConnection();
                response.connect();
                //ReportManager.log(link+"---->"+" "+response.getResponseCode()+" "+response.getResponseMessage());
                return response;
            }catch (Throwable e){
            }
        }
        return null;
    }

}
