package by.epam.viacom.cc.tests;

import by.epam.viacom.cc.utils.DriverFactory;
import by.epam.viacom.cc.utils.ThreadLocalDriver;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.json.Json;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;

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
        Thread.sleep(2000);
        driver.quit();
    }
}
