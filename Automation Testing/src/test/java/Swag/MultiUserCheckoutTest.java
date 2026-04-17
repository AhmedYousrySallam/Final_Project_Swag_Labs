package Swag;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiUserCheckoutTest extends BaseTest {

    private final By checkoutButton = By.id("checkout");
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By finishButton = By.id("finish");
    private final By completeHeader = By.cssSelector(".complete-header");

    @DataProvider(name = "checkoutUsers")
    public Object[][] checkoutUsers() {
        List<String> allowed = new ArrayList<>();
        List<String> source = Arrays.asList(LoginData.userName);
        for (String u : source) {
            if (u == null) continue;
            String name = u.trim().toLowerCase();
            if (name.equals("locked_out_user")) continue;
            allowed.add(u);
        }
        Object[][] data = new Object[allowed.size()][1];
        for (int i = 0; i < allowed.size(); i++) {
            data[i][0] = allowed.get(i);
        }
        return data;
    }

    @Test(dataProvider = "checkoutUsers", testName = "MU_CO_TC_01 - Single item checkout for multiple users")
    public void checkoutSingleItemForMultipleUsers(String username) {
        loginAs(username);
        ProductsPage products = new ProductsPage(driver);

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Should be on inventory page");

        products.addFirstItemToCart();
        products.clickCartIcon();
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.urlContains("cart.html"));
        Assert.assertEquals(products.getTitleText(), "Your Cart");

        driver.findElement(checkoutButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.urlContains("checkout-step-one.html"));
        Assert.assertEquals(products.getTitleText(), "Checkout: Your Information");

        driver.findElement(firstName).sendKeys("hager");
        driver.findElement(lastName).sendKeys("hager");
        driver.findElement(postalCode).sendKeys("10001");
        driver.findElement(continueButton).click();

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.urlContains("checkout-step-two.html"));
        Assert.assertEquals(products.getTitleText(), "Checkout: Overview");
        driver.findElement(finishButton).click();

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.urlContains("checkout-complete.html"));
        Assert.assertEquals(products.getTitleText(), "Checkout: Complete!");
        Assert.assertTrue(driver.findElement(completeHeader).isDisplayed(),
                "Completion header should be visible");
    }

    @Test(dataProvider = "checkoutUsers", testName = "MU_CO_TC_02 - Multiple items checkout for multiple users")
    public void checkoutMultipleItemsForMultipleUsers(String username) {
        loginAs(username);
        ProductsPage products = new ProductsPage(driver);

        products.addMultipleItems(2);
        Assert.assertTrue(products.getCartBadgeCount() >= 2, "Badge should reflect multiple items");

        products.clickCartIcon();
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.urlContains("cart.html"));

        driver.findElement(checkoutButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.urlContains("checkout-step-one.html"));

        driver.findElement(firstName).sendKeys("hager");
        driver.findElement(lastName).sendKeys("hager");
        driver.findElement(postalCode).sendKeys("10001");
        driver.findElement(continueButton).click();

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.urlContains("checkout-step-two.html"));
        driver.findElement(finishButton).click();

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.urlContains("checkout-complete.html"));
        Assert.assertTrue(driver.findElement(completeHeader).isDisplayed(), "Completion header should be visible");
    }
}
