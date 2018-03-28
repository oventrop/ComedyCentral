package by.epam.viacom.cc.utils;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

	private static WebDriver driver;
	private static final String GECKO_DRIVER = "D:/DATA/geckodriver.exe";
	private static final String CHROME_DRIVER = "D:/DATA/chromedriver.exe";

	private DriverFactory() {}

	public static WebDriver getWebdriver(String browser) {
		if (driver == null) {
			driver = new DriverFactory().getWebdriver(browser);
		}
		return driver;

	}

	public WebDriver selectDriver(String browser) {
		switch (browser) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", GECKO_DRIVER);
				FirefoxOptions ffOptionsoptions = new FirefoxOptions();
				driver = new FirefoxDriver(ffOptionsoptions);
			case "chrome":
				System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
				ChromeOptions chromeOptions = new ChromeOptions();
				driver = new ChromeDriver(chromeOptions);
		}
		return driver;
	}

}
