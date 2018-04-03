package by.epam.viacom.cc.tests;

import by.epam.viacom.cc.pageobjects.ToshShowVideoPlayer;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlayerRewindTest extends BaseTest {

    @Test
    public void test1() {
        driver.get("http://www.cc.com/video-clips/8pfw7w/tosh-0-twitter-reboot");
        ToshShowVideoPlayer player = new ToshShowVideoPlayer(driver);
        player.waitUntilPlayingStarted();
        player.playPauseVideo();
        player.rewindVideoToPercents(50);
        Assert.assertEquals(player.getProgressBarPosition(), "left: 50%;");

    }

}
