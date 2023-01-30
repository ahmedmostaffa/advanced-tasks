package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.element.ElementActions;

public class HomePage {
    private WebDriver driver;
    By languageLink=By.xpath("(//*[@class='dropdown'])[1]");
    By searchButton=By.cssSelector("div.search>button");
    By searchInput=By.id("txt_search_query");
    By searchMagnifier=By.id("btn_global_search");
    public HomePage(WebDriver driver){
        this.driver=driver;
    }

    /**
     * click on search button to show or make search bar visible
     * @return
     */
    public HomePage clickOnSearch(){
        ElementActions.doClick(driver,searchButton);
        return this;
    }

    /**
     *
     * @param input String the text to be entered into search bar
     * @return
     */
    public HomePage searchFor(String input){
        ElementActions.type(driver,searchInput,input);
        return this;
    }

    /**
     * set the desired language (English, Arabic ..)
     * @param language String language
     * @return
     */
    public HomePage setLanguage(String language){
        ElementActions.doClick(driver,languageLink);
        driver.findElement(By.linkText(language)).click();
        return this;
    }

    public SearchPage submit(){
        ElementActions.doClick(driver,searchMagnifier);
        return new SearchPage(driver);
    }



}
