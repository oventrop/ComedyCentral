package by.epam.viacom.cc.tests;


import by.epam.viacom.cc.jaxb.JaxbUtils;
import by.epam.viacom.cc.jaxb.TestParams;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.proxy.CaptureType;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
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
import java.io.StringReader;

public class ProxyTest {

    private WebDriver driver;
    private Har har;
    private BrowserMobProxy proxyServer;
    private static final String GECKO_DRIVER = "geckodriver";
    private static final String PLAYER_URL = "http://www.cc.com/video-clips/8pfw7w/tosh-0-twitter-reboot";
    private static final String TEST_PARAMS_XML = "TestParams.xml";

    @BeforeClass(description = "start proxy server")
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

//    @Test
//    public void proxyRequestResponceTest() {
//        proxyServer.newHar("cc.com");
//        driver.get(PLAYER_URL);
//        har = proxyServer.getHar();
//        Boolean flag = false;
//        for (HarEntry entry : har.getLog().getEntries()) {
//
//            if (entry.getRequest().getUrl().contains("http://media.mtvnservices.com/pmt/e1/access/index.html?uri")) {
//                String s = entry.getResponse().getContent().getText();
//                flag = (entry.getResponse().getStatus() == 200 && s != null && s.contains("\"timeSinceLastAd\":80000"));
//                //System.out.println(s);
//                break;
//            }
//        }
//        Assert.assertTrue(flag);
//    }

    @Test
    public void proxyJaxbSampleTest() {
        proxyServer.newHar("cc.com");
        driver.get(PLAYER_URL);
        har = proxyServer.getHar();
        Boolean flag = false;

        //new JaxbUtils().buildXML();

        for (HarEntry entry : har.getLog().getEntries()) {
            if (entry.getRequest().getUrl().contains("http://media.mtvnservices.com/pmt/e1/access/index.html?uri")) {
                String s = entry.getResponse().getContent().getText();
                TestParams params = new JaxbUtils().parseXML(TEST_PARAMS_XML);

                flag = (entry.getResponse().getStatus() == 200
                        && s != null
                        && s.contains("\"adServer\":\"" + params.getAdServer() + "\"")
                        && s.contains("\"adsEnabled\":" + params.isAdsEnabled())
                        && s.contains("\"freewheelNetworkID\":\"" + params.getFreewheelNetworkID() + "\"")
                        && s.contains("\"amazonEnabled\":" + params.isAmazonEnabled())
                );
                break;
            }
        }
        Assert.assertTrue(flag);
    }


    @AfterClass(description = "Close browser, stop server")
    public void closeBrowser() {
        driver.quit();
        proxyServer.stop();
    }
}
