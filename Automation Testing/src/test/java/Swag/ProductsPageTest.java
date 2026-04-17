package Swag;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ProductsPageTest extends BaseTest {

    @Test(testName = "PP_TC_01 - Verify Products title is displayed")
    public void verifyProductsTitle() {
        loginAs(LoginData.userName[0]);
        ProductsPage products = new ProductsPage(driver);
        Assert.assertEquals(products.getTitleText(), "Products");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @Test(testName = "PP_TC_02 - Verify cart badge increments after adding an item")
    public void verifyAddToCartBadgeIncrements() {
        loginAs(LoginData.userName[0]);
        ProductsPage products = new ProductsPage(driver);
        int before = products.getCartBadgeCount();
        products.addFirstItemToCart();
        int after = products.getCartBadgeCount();
        Assert.assertEquals(after, before + 1);
    }

    @Test(testName = "PP_TC_03 - Add to Cart changes to Remove and badge updates")
    public void addToCartChangesButtonAndBadge() {
        loginAs(LoginData.userName[0]);
        ProductsPage products = new ProductsPage(driver);
        Assert.assertTrue(products.getFirstItemButtonText().equalsIgnoreCase("Add to cart"));
        int beforeBadge = products.getCartBadgeCount();
        products.addFirstItemToCart();
        Assert.assertTrue(products.getFirstItemButtonText().equalsIgnoreCase("Remove"));
        Assert.assertEquals(products.getCartBadgeCount(), beforeBadge + 1);
    }

    @Test(testName = "PP_TC_04 - Add multiple items updates badge count accordingly")
    public void addMultipleItemsIncrementsBadge() {
        loginAs(LoginData.userName[0]);
        ProductsPage products = new ProductsPage(driver);
        int before = products.getCartBadgeCount();
        products.addMultipleItems(3);
        Assert.assertEquals(products.getCartBadgeCount(), before + 3);
    }

    @Test(testName = "PP_TC_05 - Removing an item reverts its button back to Add to cart")
    public void removeRevertsButtonToAdd() {
        loginAs(LoginData.userName[0]);
        ProductsPage products = new ProductsPage(driver);
        products.addFirstItemToCart();
        products.removeFirstItemFromCart();
        Assert.assertTrue(products.getFirstItemButtonText().equalsIgnoreCase("Add to cart"));
    }

    @Test(testName = "PP_TC_06 - Removing last item hides the cart badge")
    public void removingLastItemHidesBadge() {
        loginAs(LoginData.userName[0]);
        ProductsPage products = new ProductsPage(driver);
        products.removeAllItemsFromCart();
        Assert.assertFalse(products.isCartBadgeVisible());
        products.addFirstItemToCart();
        Assert.assertTrue(products.isCartBadgeVisible());
        products.removeFirstItemFromCart();
        Assert.assertFalse(products.isCartBadgeVisible());
    }

    @Test(testName = "PP_TC_07 - Verify sorting Name (Z to A) changes first item")
    public void verifySortingZToAChangesOrder() {
        loginAs(LoginData.userName[0]);
        ProductsPage products = new ProductsPage(driver);
        String before = products.getFirstItemName();
        products.sortByNameZToA();
        String after = products.getFirstItemName();
        Assert.assertNotEquals(after, before);
    }

    @Test(testName = "PP_TC_08 - Verify logout from products page returns to login")
    public void verifyLogout() {
        loginAs(LoginData.userName[0]);
        ProductsPage products = new ProductsPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.title")));
        products.logout();
        Assert.assertTrue(new LoginPage(driver).isLoginButtonDisplayed());
    }

    @Test(testName = "PP_TC_09 - State persists on refresh: Verify cart state after refresh")
    public void statePersistsOnRefresh() {
        loginAs(LoginData.userName[0]);
        ProductsPage products = new ProductsPage(driver);
        products.removeAllItemsFromCart();
        products.addFirstItemToCart();
        int beforeRefreshBadge = products.getCartBadgeCount();
        driver.navigate().refresh();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.title")));
        Assert.assertEquals(products.getCartBadgeCount(), beforeRefreshBadge);
        Assert.assertTrue(products.getFirstItemButtonText().equalsIgnoreCase("Remove"));
    }

    @Test(testName = "PP_TC_10 - Cart navigation: Cart icon redirects to Cart page")
    public void cartIconNavigatesToCartPage() {
        loginAs(LoginData.userName[0]);
        ProductsPage products = new ProductsPage(driver);
        products.clickCartIcon();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("cart.html"));
        Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"));
        Assert.assertEquals(products.getTitleText(), "Your Cart");
    }

    @Test(testName = "PP_TC_11 - Cart content matches badge: Ensure cart matches badge count")
    public void cartContentMatchesBadge() {
        loginAs(LoginData.userName[0]);
        ProductsPage products = new ProductsPage(driver);
        products.removeAllItemsFromCart();
        products.addMultipleItems(3);
        int badge = products.getCartBadgeCount();
        products.clickCartIcon();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("cart.html"));
        Assert.assertEquals(products.getCartItemsCount(), badge);
    }

    @Test(testName = "PP_TC_12 - Add same product multiple times: Ensure product not duplicated")
    public void addingSameProductIsNotDuplicated() {
        loginAs(LoginData.userName[0]);
        ProductsPage products = new ProductsPage(driver);
        products.removeAllItemsFromCart();
        String firstName = products.getFirstItemName();
        products.addFirstItemToCart();
        products.clickCartIcon();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("cart.html"));
        List<String> names = products.getCartItemNames();
        long occurrences = names.stream().filter(n -> n.equalsIgnoreCase(firstName)).count();
        Assert.assertEquals(occurrences, 1);
    }
}
