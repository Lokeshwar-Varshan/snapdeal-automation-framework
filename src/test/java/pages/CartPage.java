package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

public class CartPage extends BasePage {

    // Locator
    By addToCartBtn = By.id("add-cart-button-id");
    By cartModal = By.cssSelector("div.modal-box-dialog.in");
    By cartHeader = By.xpath("//h3[contains(text(),'Shopping Cart')]");
    By viewCartBtn = By.xpath("//div[contains(@class,'open-cart') and normalize-space()='View Cart']");
    By cartBanner = By.xpath("//*[contains(text(),'already present in your cart') or contains(text(),'added to your cart')]");
    By cartItems = By.cssSelector("li.cart-item");

    public boolean isAddToCartVisible() {
        return WaitUtils.waitForElement(driver, addToCartBtn).isDisplayed();
    }

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart() {
        WaitUtils.waitForElement(driver, addToCartBtn).click();
    }

    public void waitForCartModal() {

        WaitUtils.getWait(driver)
                .until(ExpectedConditions.visibilityOfElementLocated(cartModal));

        System.out.println("Cart modal is visible");
    }


    public void waitForCartContent() {


        WaitUtils.getWait(driver)
                .until(ExpectedConditions.visibilityOfElementLocated(cartHeader));

        System.out.println("Cart content fully loaded");
    }


    public void clickViewCart() {

        try {
            WebDriverWait wait = WaitUtils.getWait(driver);

            wait.until(ExpectedConditions.visibilityOfElementLocated(cartBanner));

            WebElement viewCart = wait.until(
                    ExpectedConditions.elementToBeClickable(viewCartBtn)
            );

            new Actions(driver)
                    .moveToElement(viewCart)
                    .click()
                    .perform();

            System.out.println("View Cart clicked via Actions");

        } catch (Exception e) {
            System.out.println("View Cart click failed");
            e.printStackTrace();
        }
    }

    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }
}