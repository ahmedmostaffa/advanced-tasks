package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.element.ElementActions;
import utilities.element.JavaScriptManager;

import java.util.List;

public class LessonPage {
    private WebDriver driver;
    public By pageHeader=By.cssSelector("div.page-title.with-cta>h1");
    By worksheetLink=By.linkText("Lesson Worksheet");
    By worksheetSection=By.cssSelector("div.question-preview>a");
    By questions=By.cssSelector("div.question.inline-displayed");
    public LessonPage (WebDriver driver){
        this.driver=driver;
    }

    /**
     * scroll to the worksheet section then click on preview button
     * @return
     */
    public LessonPage goToWorksheet(){
        JavaScriptManager.scrollToElement(driver,worksheetSection);
        ElementActions.doClick(driver,worksheetSection);
        return this;
    }

    /**
     * get the list of available questions on worksheet home page
     * @return
     */
    public List<WebElement> getQuestions(){
        List<WebElement> elements=driver.findElements(questions);
        return elements;
    }

    /**
     * get the number of the available questions
     * @return
     */
    public int getQuestionsCount(){
        return getQuestions().size();
    }




}
