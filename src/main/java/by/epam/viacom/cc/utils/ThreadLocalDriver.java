package by.epam.viacom.cc.utils;


import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;

public class ThreadLocalDriver {

    private static final ThreadLocal<AndroidDriver> THREAD = new ThreadLocal<>();

    public static AndroidDriver getDriver() {
        return THREAD.get();
    }

    public static void setWebDriver(AndroidDriver driver) {
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
