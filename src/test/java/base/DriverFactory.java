package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver initDriver() {

        // Get browser from config
        String browser = BaseTest.prop.getProperty("browser");

        // Initialize driver based on browser
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        // (Optional: future support)
        // else if (browser.equalsIgnoreCase("edge")) {
        //     WebDriverManager.edgedriver().setup();
        //     driver = new EdgeDriver();
        // }

        // Maximize window
        driver.manage().window().maximize();

        // Get timeout from config
        int timeout = Integer.parseInt(BaseTest.prop.getProperty("timeout"));

        // Apply timeout
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));

        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}