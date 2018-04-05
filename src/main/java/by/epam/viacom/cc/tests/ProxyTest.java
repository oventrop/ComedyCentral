package by.epam.viacom.cc.tests;


import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class ProxyTest {

    private WebDriver driver;
    private Har har;
    private BrowserMobProxy proxyServer;
    private static final String GECKO_DRIVER = "D:/DATA/geckodriver.exe";
    private static final String PLAYER_URL = "http://www.cc.com/video-clips/8pfw7w/tosh-0-twitter-reboot";
    private static final String HAR_PATH = "D:/Test.har";

    @BeforeClass(description = "server start")
    public void startServer() {
        proxyServer = new BrowserMobProxyServer();
        proxyServer.start(4444);
        proxyServer.setHarCaptureTypes(CaptureType.RESPONSE_HEADERS, CaptureType.RESPONSE_CONTENT);
    }

    @BeforeClass(dependsOnMethods = "startServer")
    public void startBrowser() {
        Proxy seleniumProxy = new Proxy();
        seleniumProxy.setHttpProxy("localhost:4444").setFtpProxy("localhost:4444");
        System.setProperty("webdriver.gecko.driver", GECKO_DRIVER);
        FirefoxOptions options = new FirefoxOptions();
        options.setProxy(seleniumProxy);
        driver = new FirefoxDriver(options);
    }

    @BeforeClass(dependsOnMethods = "startBrowser")
    public void harInitialize() {
        proxyServer.newHar("cc.com");
        driver.get(PLAYER_URL);
        har = proxyServer.getHar();
        try {
            har.writeTo(new File(HAR_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void proxyRequestTest() {
        Boolean flag = false;
        for (HarEntry entry : har.getLog().getEntries()) {
            if (entry.getRequest().getUrl().contains("http://media.mtvnservices.com/pmt/e1/access/index.html?uri")) {
                System.out.println(entry.getResponse().getStatus());
                flag = (entry.getResponse().getStatus() == 200);
                break;
            }
        }
        Assert.assertTrue(flag);
    }

    @Test
    public void proxyResponceTest() {
        Boolean flag = false;
        for (HarEntry entry : har.getLog().getEntries()) {
            String s = entry.getResponse().getContent().getText();
            if (s != null && s.contains("\"timeSinceLastAd\":80000")) {
                flag = true;
                break;
            }
        }
        Assert.assertTrue(flag, "Text not found!");
    }

    @AfterClass(description = "Close browser, stop server")
    public void closeBrowser() {
        driver.quit();
        proxyServer.stop();
    }
}
