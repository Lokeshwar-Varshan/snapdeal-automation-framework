package pages;

import org.openqa.selenium.*;
import utils.WaitUtils;

public class ProductPage extends BasePage {


    // Locator
    By firstProduct = By.cssSelector("div.product-tuple-description a");

    By productList = By.cssSelector("div.product-tuple-description");

    public void waitForProductList() {
        WaitUtils.waitForElements(driver, productList);
    }

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void clickFirstProduct() {
        WaitUtils.waitForElement(driver, firstProduct).click();
    }

    public boolean isProductListDisplayed() {
        return driver.findElements(productList).size() > 0;
    }
}