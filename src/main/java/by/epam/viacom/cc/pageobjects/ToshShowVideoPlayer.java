package by.epam.viacom.cc.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ToshShowVideoPlayer extends AbstractPage {

    public ToshShowVideoPlayer(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='media-container']")
    private WebElement playerContainer;

    @FindBy(xpath = "//div[@class='edge-gui-playback-button']")
    private WebElement playButton;

    @FindBy(xpath = "//div[@class='edge-gui-progress-bar']")
    private WebElement progressBar;

    @FindBy(xpath = "//div[@class='edge-gui-progress-bar-thumb']")
    private WebElement progressBarPosition;

    @FindBy(xpath = "//div[@class='edge-gui-fullscreen-button']")
    private WebElement fullscreenButton;

    @FindBy(xpath = "//div[contains(@class, 'fullscreen-active-state')]")
    private WebElement fullscreenState;

    @FindBy(xpath = "//div[@class = 'logo']")
    private WebElement pageLogo;


    public boolean playerPageCorrectLoad() {
        return isElementPresent(playerContainer);
    }

    public ToshShowVideoPlayer waitUntilPlayingStarted() {
        Wait wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofSeconds(15)).ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.elementToBeClickable(playButton));
        return new ToshShowVideoPlayer(driver);
    }

    public ToshShowVideoPlayer playPauseVideo() {
        Actions actions = new Actions(driver);
        actions.moveByOffset(0, 0);
        actions.moveToElement(playerContainer).build().perform();
        playButton.click();
        return new ToshShowVideoPlayer(driver);
    }

    public ToshShowVideoPlayer rewindVideoToPercents(int position) {
        if (position > 100 || position <= 0) {
            throw new IllegalArgumentException();
        }
        Actions actions = new Actions(driver);
        int progressBarLength = progressBar.getSize().width;
        System.out.println(progressBarLength);
        int rewindPosition = (progressBarLength * position) / 100;
        if (driver instanceof FirefoxDriver) {
            actions.moveToElement(progressBar, (-progressBarLength / 2), 0).build().perform();
        } else if (driver instanceof ChromeDriver) {
            actions.moveToElement(progressBar, 0, 0).build().perform();
        }
        actions.moveByOffset(rewindPosition, 0).click().build().perform();
        System.out.println(progressBarPosition.getAttribute("style"));
        return new ToshShowVideoPlayer(driver);
    }

    public String getProgressBarPosition() {
        return progressBarPosition.getAttribute("style");
    }

    public ToshShowVideoPlayer switchPlayerToFullScreen() {
        Actions actions = new Actions(driver);
        actions.moveByOffset(0, 0);
        actions.moveToElement(playerContainer).build().perform();
        actions.moveToElement(fullscreenButton).click().build().perform();
        return new ToshShowVideoPlayer(driver);
    }

    public boolean isVideoFullScreen() {
        return isElementPresent(fullscreenState);
    }


}
