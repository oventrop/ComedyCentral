package by.epam.viacom.cc.tests;


import by.epam.viacom.cc.jaxb.JaxbUtils;
import by.epam.viacom.cc.jaxb.ProxyTestDataProvider;
import by.epam.viacom.cc.jaxb.TestParams;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProxyTest {

    private WebDriver driver;
    private Har har;
    private BrowserMobProxy proxyServer;
    private static final String GECKO_DRIVER = "geckodriver";
    private static final String PLAYER_URL = "http://www.cc.com/video-clips/8pfw7w/tosh-0-twitter-reboot";
    // private static final String TEST_PARAMS_XML = "TestPass1.xml";

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

    @Test(dataProvider = "XmlDataProvider", dataProviderClass = ProxyTestDataProvider.class)
    public void proxyJaxbSampleTest(TestParams params) {
        proxyServer.newHar("cc.com");
        driver.get(PLAYER_URL);
        har = proxyServer.getHar();
        String responce = "";
        int status = 0;

        for (HarEntry entry : har.getLog().getEntries()) {
            if (entry.getRequest().getUrl().contains("http://media.mtvnservices.com/pmt/e1/access/index.html?uri")) {
                responce = entry.getResponse().getContent().getText();
                status = entry.getResponse().getStatus();
                break;
            }//add softAsserts, 4 xml suits, params out of main class, suit передать в сигнатуру тества
        }

        SoftAssert assertion = new SoftAssert();
        assertion.assertEquals(status, 200);
        assertion.assertNotNull(responce);
        assertion.assertTrue(responce.contains("\"adServer\":\"" + params.getAdServer() + "\""));
        assertion.assertTrue(responce.contains("\"adsEnabled\":" + params.isAdsEnabled()));
        assertion.assertTrue(responce.contains("\"freewheelNetworkID\":\"" + params.getFreewheelNetworkID() + "\""));
        assertion.assertTrue(responce.contains("\"amazonEnabled\":" + params.isAmazonEnabled()));
        assertion.assertTrue(responce.contains("\"timeSinceLastAd\":80000"));
        assertion.assertAll();
    }


    @AfterClass(description = "Close browser, stop server")
    public void closeBrowser() {
        driver.quit();
        proxyServer.stop();
    }
}
