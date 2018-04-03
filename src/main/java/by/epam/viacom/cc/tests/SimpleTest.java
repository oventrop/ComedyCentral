package by.epam.viacom.cc.tests;

import by.epam.viacom.cc.pageobjects.MainPage;
import by.epam.viacom.cc.pageobjects.ShowsPage;
import by.epam.viacom.cc.pageobjects.ToshShowVideoPlayer;
import by.epam.viacom.cc.pageobjects.ToshShowsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleTest extends BaseTest{

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
        player.rewindVideoToPercents(101);
        player.switchPlayerToFullScreen();
        Assert.assertTrue(player.isVideoFullScreen(), "Video is not fullScreen");

    }



}
