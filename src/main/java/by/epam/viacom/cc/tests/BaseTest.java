package by.epam.viacom.cc.tests;

import by.epam.viacom.cc.utils.DriverFactory;
import by.epam.viacom.cc.utils.ThreadLocalDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {
    WebDriver driver;
    private static final String URL = "https://www.cc.com";

    @Parameters({"browser"})
    @BeforeClass
    public void startTest(String browser) {
        driver = DriverFactory.getDriver(browser);
        ThreadLocalDriver.setWebDriver(driver);
        driver = ThreadLocalDriver.getDriver();
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @AfterClass(description = "Close browser")
    public void closeBrowser() throws InterruptedException {
        driver.quit();
        ThreadLocalDriver.removeDriverThread();
    }
}
