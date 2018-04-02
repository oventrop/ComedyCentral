package by.epam.viacom.cc.utils;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class DriverFactory {

    private WebDriver driver;
    private static final String GECKO_DRIVER = "D:/DATA/geckodriver.exe";
    private static final String CHROME_DRIVER = "D:/DATA/chromedriver.exe";

    public WebDriver selectDriver(String browser) {
        switch (browser) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", GECKO_DRIVER);
                FirefoxOptions ffoptions = new FirefoxOptions();
                driver = new FirefoxDriver(ffoptions);
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
                ChromeOptions chromeOptions = new ChromeOptions();
                driver = new ChromeDriver(chromeOptions);
                break;
        }
        return driver;
    }
}
