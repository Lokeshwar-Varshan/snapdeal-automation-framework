package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BaseTest {

    private static final Logger log = LogManager.getLogger(BaseTest.class);

    protected WebDriver driver;
    public static Properties prop;

    @BeforeMethod
    public void setup() {
        loadConfig();

        log.info("Initializing WebDriver");

        driver = DriverFactory.initDriver();

        log.info("Launching application: " + prop.getProperty("baseURL"));

        driver.get(prop.getProperty("baseURL"));

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    }

    public void loadConfig() {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            prop.load(fis);
        } catch (Exception e) {
            log.error("Error loading config file", e);
        }
    }

    @AfterMethod
    public void tearDown() {

        String autoClose = prop.getProperty("autoClose");

        if (autoClose.equalsIgnoreCase("true")) {
            log.info("Closing browser");
            DriverFactory.quitDriver();
        } else {
            log.info("Browser left open for debugging");
        }
    }
}