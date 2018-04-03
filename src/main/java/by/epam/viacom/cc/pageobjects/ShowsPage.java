package by.epam.viacom.cc.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShowsPage extends AbstractPage {


    public ShowsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h3[text() = 'Tosh.0' ]")
    private WebElement toshShowButton;


    public ToshShowsPage openTosh0Show() {
        toshShowButton.click();
        return new ToshShowsPage(driver);
    }

}
