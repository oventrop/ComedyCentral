package by.epam.viacom.cc.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class MainPage extends AbstractPage {

    @FindBy(xpath = "//div[@class = 'logo_anim']")
    private WebElement logo;

    @FindBy(xpath = "//a[@data-link= 'shows']")
    private WebElement showsButton;

    @FindBy(xpath = "//a[@class = 'see_all' and contains (text(), 'See all Shows')]")
    private WebElement showAllShowsButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public boolean mainPageCorrectLoad() {
        return isElementPresent(logo);
            }

    public ShowsPage openAllShowsPageThroughRollMenu() {
        Actions action = new Actions(driver);
        action.moveToElement(showsButton).build().perform();
        showAllShowsButton.click();
        return new ShowsPage(driver);
    }

    public ShowsPage openAllShows() {
        showsButton.click();
        return new ShowsPage(driver);
    }
}
