package by.epam.viacom.cc.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

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
