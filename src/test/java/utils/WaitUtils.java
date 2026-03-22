package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class WaitUtils {

    public static WebDriverWait getWait(WebDriver driver) {

        int timeout = Integer.parseInt(base.BaseTest.prop.getProperty("timeout"));

        return new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public static WebElement waitForElement(WebDriver driver, By locator) {

        return getWait(driver)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForElements(WebDriver driver, By locator) {

        getWait(driver)
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0));
    }

}