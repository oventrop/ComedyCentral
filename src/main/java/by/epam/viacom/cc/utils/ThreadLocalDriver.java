package by.epam.viacom.cc.utils;


import org.openqa.selenium.WebDriver;

public class ThreadLocalDriver {

    private static final ThreadLocal<WebDriver> THREAD = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return THREAD.get();
    }

    public static void setWebDriver(WebDriver driver) {
        if (THREAD.get() == null) {
            THREAD.set(driver);
        }
    }

    public static void removeDriverThread() {
        if (THREAD.get() != null) {
            THREAD.remove();
        }
    }
}
