package by.epam.viacom.cc.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ToshShowsPage extends AbstractPage {


    public ToshShowsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[text() = 'Tosh.0']")
    WebElement toshShowTitleElement;

    @FindBy(xpath = "//span[@class='title']")
    List<WebElement> shows;

    public boolean isToshShowPageLoaded() {
       return isElementPresent(toshShowTitleElement);
     // return driver.getTitle().matches("^.*Tosh\\.0.*$");
    }

    public ToshShowVideoPlayer openFirstShow() {
        System.out.println(shows.size());
        shows.get(0).click();
        return new ToshShowVideoPlayer(driver);
    }

}
