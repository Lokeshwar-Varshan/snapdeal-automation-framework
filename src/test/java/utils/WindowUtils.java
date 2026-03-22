package utils;

import org.openqa.selenium.WebDriver;

public class WindowUtils {

    public static void switchToNewTab(WebDriver driver) {

        String parent = driver.getWindowHandle();

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(parent)) {
                driver.switchTo().window(handle);
            }
        }
    }
}