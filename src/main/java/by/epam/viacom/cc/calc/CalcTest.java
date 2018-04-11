package by.epam.viacom.cc.calc;


import by.epam.viacom.cc.pageobjects.AbstractPage;
import by.epam.viacom.cc.pageobjects.MainPage;
import by.epam.viacom.cc.utils.DriverFactory;
import by.epam.viacom.cc.utils.ThreadLocalDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CalcTest {

    AndroidDriver driver;



    @FindBy(id = "com.sec.android.app.popupcalculator:id/bt_02")
    WebElement two;

    @FindBy(id = "com.sec.android.app.popupcalculator:id/bt_04")
    WebElement four;

    @FindBy(id = "com.sec.android.app.popupcalculator:id/bt_mul")
    WebElement mult;

    @FindBy(id = "com.sec.android.app.popupcalculator:id/bt_add")
    WebElement plus;

    @FindBy(id = "com.sec.android.app.popupcalculator:id/bt_parenthesis")
    WebElement brackets;

    @FindBy(id = "com.sec.android.app.popupcalculator:id/bt_equal")
    WebElement equal;

    @FindBy(id = "com.sec.android.app.popupcalculator:id/bt_clear")
    WebElement clear;

    @FindBy(id = "com.sec.android.app.popupcalculator:id/txtCalc")
    WebElement result;


    @BeforeClass
    public void startTest() {
        driver = DriverFactory.getDriver();
        ThreadLocalDriver.setWebDriver(driver);
        driver = ThreadLocalDriver.getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }

    @Test
    public void calcTest() throws InterruptedException {

        clear.click();
        brackets.click();
        two.click();
        plus.click();
        two.click();
        brackets.click();
        mult.click();
        four.click();
        equal.click();

        Assert.assertEquals(result.getText(), "16");
    }

    @AfterClass(description = "Close browser")
    public void closeBrowser() throws InterruptedException {
        driver.quit();
        ThreadLocalDriver.removeDriverThread();
    }


}
