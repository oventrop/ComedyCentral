package by.epam.viacom.cc.utils;


import org.openqa.selenium.WebDriver;

public class ThreadLocalDriver {

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    public static void setWebDriver(WebDriver driver) {
        if (webDriver.get() == null) {
            webDriver.set(driver);
        }
    }
}
