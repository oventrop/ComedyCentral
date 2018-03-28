package by.epam.viacom.cc.utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

    WebDriver driver;

    public Utils(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementPresent(WebElement element) {
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
