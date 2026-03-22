package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.Keys;
import utils.WaitUtils;

public class SearchPage extends BasePage {


    // Locators
    By searchBox = By.id("search-box-input");

    // Constructor
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    // Actions
    public void searchProduct(String product) {

        WebElement search = WaitUtils.waitForElement(driver, searchBox);

        search.sendKeys(product);

        // Press ENTER instead of clicking button
        search.sendKeys(Keys.ENTER);
    }
}