package by.epam.viacom.cc.tests;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class ProxyTest {

    WebDriver driver;
    Proxy proxy;
    private ProxyServer server;
    private static final String GECKO_DRIVER = "D:/DATA/geckodriver.exe";

    @BeforeClass(description = "server start")
    public void startServer() {
        server = new ProxyServer(4444);
        server.start();
        proxy = server.seleniumProxy();
        server.newHar("cc.com");
     }

    @BeforeClass(dependsOnMethods = "startServer")
    public void startBrowser() {
        System.setProperty("webdriver.gecko.driver", GECKO_DRIVER);
        FirefoxOptions options = new FirefoxOptions();
        options.setProxy(proxy);
        driver = new FirefoxDriver(options);
    }

    @Test
    public void proxyServerTest() {

        driver.get("http://www.cc.com/video-clips/8pfw7w/tosh-0-twitter-reboot");
        Har har = server.getHar();
        try {
            har.writeTo(new File("D://Test.har"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        har.getLog().getEntries();
        for (HarEntry entry : har.getLog().getEntries()) {

            if (entry.getRequest().getUrl().contains("http://media.mtvnservices.com/pmt/e1/access/index.html?uri")) {
                System.out.println(entry.getResponse());
                System.out.println(entry.getResponse().getStatus());
                            }
        }
    }


    @AfterClass(description = "Close browser")
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
        server.stop();
    }
}
