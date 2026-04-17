package Swag;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckoutFlowTest extends BaseTest {

    private final By checkoutButton = By.id("checkout");
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By finishButton = By.id("finish");
    private final By completeHeader = By.cssSelector(".complete-header");

    @Test(testName = "CO_TC_01 - Complete checkout with one item")
    public void completeCheckoutWithOneItem() {
        completeCheckoutWithItems(1, "12345");
    }

    @Test(testName = "CO_TC_02 - Complete checkout with multiple items")
    public void completeCheckoutWithMultipleItems() {
        completeCheckoutWithItems(2, "90210");
    }

    @Test(testName = "CO_TC_03 - Complete checkout with three items")
    public void completeCheckoutWithThreeItems() {
        completeCheckoutWithItems(3, "33333");
    }

    @Test(testName = "CO_TC_04 - Complete checkout with four items")
    public void completeCheckoutWithFourItems() {
        completeCheckoutWithItems(4, "44444");
    }

    @Test(testName = "CO_TC_05 - Complete checkout with all items")
    public void completeCheckoutWithAllItems() {
        loginAs(LoginData.userName[0]);
        ProductsPage products = new ProductsPage(driver);

        products.addAllItems();
        Assert.assertTrue(products.getCartBadgeCount() >= 1, "Badge should reflect items added");
        proceedThroughCheckout(products, "99999");
    }

    private void completeCheckoutWithItems(int itemCount, String postal) {
        loginAs(LoginData.userName[0]);
        ProductsPage products = new ProductsPage(driver);

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Should be on inventory page");
        products.addMultipleItems(itemCount);
        Assert.assertTrue(products.getCartBadgeCount() >= itemCount, "Badge should reflect selected items");

        proceedThroughCheckout(products, postal);
    }

    private void proceedThroughCheckout(ProductsPage products, String postal) {
        products.clickCartIcon();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("cart.html"));
        Assert.assertEquals(products.getTitleText(), "Your Cart");

        driver.findElement(checkoutButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("checkout-step-one.html"));
        Assert.assertEquals(products.getTitleText(), "Checkout: Your Information");

        driver.findElement(firstName).sendKeys("hager");
        driver.findElement(lastName).sendKeys("hager");
        driver.findElement(postalCode).sendKeys(postal);
        driver.findElement(continueButton).click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("checkout-step-two.html"));
        Assert.assertEquals(products.getTitleText(), "Checkout: Overview");

        driver.findElement(finishButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("checkout-complete.html"));
        Assert.assertEquals(products.getTitleText(), "Checkout: Complete!");
        Assert.assertTrue(driver.findElement(completeHeader).isDisplayed(),
                "Completion header should be visible");
    }
}
