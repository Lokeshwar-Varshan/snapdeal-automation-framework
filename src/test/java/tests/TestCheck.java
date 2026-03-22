package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.AIUtils;
import org.testng.annotations.Listeners;
import listeners.TestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.WindowUtils;


@Listeners(TestListener.class)
public class TestCheck extends BaseTest {

    private static final Logger log = LogManager.getLogger(TestCheck.class);

    @Test
    public void testSearchAndAddToCart() {

        log.info("Test started");

        SearchPage searchPage = new SearchPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        // Step 1: Search
        String product = AIUtils.getAISuggestedProduct();

        log.info("AI-driven test data selection initiated");

        log.info("AI Generated product: " + product);
        searchPage.searchProduct(product);

        log.info("Search completed");

        //assert false; //for screenshot

        // Step 2: Wait
        productPage.waitForProductList();
        log.info("Search results loaded");

        // Step 3: Validate
        Assert.assertTrue(productPage.isProductListDisplayed(),
                "Product list is not displayed");
        log.info("Search results validated");

        // Step 4: Click product
        productPage.clickFirstProduct();
        log.info("Product clicked");

        // Step 5: Switch tab
        WindowUtils.switchToNewTab(driver);

        log.info("Switched to product tab: " + driver.getCurrentUrl());

        // Step 6: Validate page
        Assert.assertTrue(cartPage.isAddToCartVisible(),
                "Add to Cart button not visible");
        log.info("Product page validated");

        // Step 7: Add to cart
        cartPage.addToCart();
        log.info("Add to cart clicked");

        // Step 8: Click to view the cart
        cartPage.clickViewCart();
        log.info("View Cart clicked");

        // Step 9: Wait for cart modal to appear
        cartPage.waitForCartModal();

        // Step 10: Wait for cart content to fully load
        cartPage.waitForCartContent();

        // Step 11: Get the number of items in the cart
        int itemCount = cartPage.getCartItemCount();

        // Step 12: Validate that cart has at least one item
        Assert.assertTrue(itemCount >= 1,
                "Cart does not contain any items");

        // Step 13: Log the number of items present in the cart
        log.info("Cart contains items: " + itemCount);

        // Step 14: Confirm cart is fully displayed
        log.info("Cart fully displayed");
    }

}