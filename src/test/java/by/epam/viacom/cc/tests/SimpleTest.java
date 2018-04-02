package by.epam.viacom.cc.tests;

import by.epam.viacom.cc.pageobjects.MainPage;
import by.epam.viacom.cc.pageobjects.ShowsPage;
import by.epam.viacom.cc.pageobjects.ToshShowVideoPlayer;
import by.epam.viacom.cc.pageobjects.ToshShowsPage;
import by.epam.viacom.cc.utils.DriverFactory;
import by.epam.viacom.cc.utils.ThreadLocalDriver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SimpleTest {

    WebDriver driver;
    private static final String URL = "https://www.cc.com";

    @Parameters({"browser"})
    @BeforeClass
    public void startTest(String browser) {
        driver = new DriverFactory().selectDriver(browser);
        ThreadLocalDriver.setWebDriver(driver);
        driver = ThreadLocalDriver.getDriver();
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @Test
    public void playerPageCorrectLoad() {
        MainPage mainPage = new MainPage(driver);
        System.out.println(driver.getTitle());
        Assert.assertTrue(mainPage.isMainPageLoaded(), "Not MAIN page loaded");
        //   Assert.assertTrue(false);

        ShowsPage showsPage = mainPage.openAllShows();
        Assert.assertTrue(driver.getCurrentUrl().equals("http://www.cc.com/shows"), "Not SHOWS page loaded");

        ToshShowsPage toshShowsPage = showsPage.openTosh0Show();
        Assert.assertTrue(toshShowsPage.isToshShowPageLoaded(), "Not TOSHSHOW page loaded");

        ToshShowVideoPlayer player = toshShowsPage.openFirstShow();
        Assert.assertTrue(player.playerPageCorrectLoad(), "Not PLAYER page loaded");


        player.waitUntilPlayingStarted();
        System.out.println("Playing started!");

        player.playPauseVideo();
        player.rewindVideo(500);
        player.switchPlayerToFullScreen();
        Assert.assertTrue(player.isVideoFullScreen(), "Video is not fullScreen");

    }


    @AfterClass(description = "Close browser")
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
        //DriverFactory.closeWebBrowser();
    }
}
