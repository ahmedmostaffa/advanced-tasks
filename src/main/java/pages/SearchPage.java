package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.element.ElementActions;

import java.util.List;

public class SearchPage {
    private WebDriver driver;

    By allLinks=By.xpath("//div[@class='right']/a");
    public By secondLink=By.xpath("(//div[@class='right']/a)[2]");

    public SearchPage(WebDriver driver){
        this.driver=driver;
    }

    /**
     * click on n-th link at the search result page
     * @return
     */
    public LessonPage clickOnLink(int linkNumber){
        driver.findElements(allLinks).get(linkNumber-1).click();
        return new LessonPage(driver);
    }

    /**
     * get the innerText of every link at the search result page
     * @return
     */

    public List<String> getAllLinks(){
        return ElementActions.getTextOfEveryElement(driver,allLinks);
    }

}
