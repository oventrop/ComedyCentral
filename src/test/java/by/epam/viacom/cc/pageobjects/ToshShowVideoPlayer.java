package by.epam.viacom.cc.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;


import java.util.concurrent.TimeUnit;

public class ToshShowVideoPlayer extends AbstractPage {

    public ToshShowVideoPlayer(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='media-container']")
    private WebElement playerContainer;

    @FindBy(xpath = "//div[@class='edge-gui-playback-button']")
    private WebElement playButton;

    @FindBy(xpath = "//div[@class='edge-gui-progress-bar-thumb']")
    private WebElement progressBar;

    @FindBy(xpath = "//div[@class='edge-gui-fullscreen-button']")
    private WebElement fullscreenButton;

    @FindBy(xpath = "//div[contains(@class, 'fullscreen-active-state')]")
    private WebElement fullscreenState;

    public boolean playerPageCorrectLoad() {
        return isElementPresent(playerContainer);
    }

    public ToshShowVideoPlayer waitUntilPlayingStarted() {
        Wait wait = new FluentWait<WebDriver>(driver)
                .withTimeout(15, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.elementToBeClickable(playButton));
        return new ToshShowVideoPlayer(driver);
    }

    public ToshShowVideoPlayer playPauseVideo() {
        Actions actions = new Actions(driver);
        actions.moveByOffset(0,0);
        actions.moveToElement(playerContainer).build().perform();
        playButton.click();
        return new ToshShowVideoPlayer(driver);
    }

    public ToshShowVideoPlayer rewindVideo(int xOffset) {
        Actions actions = new Actions(driver);
        actions.moveByOffset(0,0);
        actions.moveToElement(playerContainer).build().perform();
        actions.moveToElement(progressBar).moveByOffset(xOffset, 0).click().build().perform();
        return new ToshShowVideoPlayer(driver);
    }

    public ToshShowVideoPlayer fulscreenVideo() {
        Actions actions = new Actions(driver);
        actions.moveByOffset(0,0);
        actions.moveToElement(playerContainer).build().perform();
        actions.moveToElement(fullscreenButton).click().build().perform();
        return new ToshShowVideoPlayer(driver);
    }

    public boolean isVideoFullScreen() {
        return isElementPresent(fullscreenState);
    }


}
