package by.epam.viacom.cc.tests;

import by.epam.viacom.cc.pageobjects.MainPage;
import by.epam.viacom.cc.pageobjects.ToshShowVideoPlayer;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleEmptyTest extends BaseTest {

    @Test
    public void test1() {
        MainPage mainPage = new MainPage(driver);
        System.out.println(driver.getTitle());
        Assert.assertTrue(mainPage.isMainPageLoaded(), "Not MAIN page loaded");
    }
}
