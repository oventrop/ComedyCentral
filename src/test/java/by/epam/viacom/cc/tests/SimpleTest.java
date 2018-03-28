package by.epam.viacom.cc.tests;

import by.epam.viacom.cc.pageobjects.MainPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleTest {

    WebDriver driver;

    @Test
    public void mainPageCorrectLoad (){

        Assert.assertTrue(new MainPage(driver).mainPageCorrectLoad(), "Wrong page loaded");
    }
}
