package by.epam.viacom.cc.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ToshShowVideoPlayer extends AbstractPage {

    public ToshShowVideoPlayer(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='media-container']")
    WebElement playerContainer;

    public boolean playerPageCorrectLoad(){
        return isElementPresent(playerContainer);
    }
}
