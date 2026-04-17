package Swag;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test(testName = "LP_TC_01 - Verify login works with standard user")
    public void standardUserCanLogin() {
        LoginPage loginPage = openLoginPage();
        loginPage.login(LoginData.userName[0], LoginData.password);
        waitForProductsPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test(testName = "LP_TC_02 - Verify locked out user sees an error message")
    public void lockedOutUserSeesErrorMessage() {
        LoginPage loginPage = openLoginPage();
        loginPage.login(LoginData.userName[1], LoginData.password);
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test(testName = "LP_TC_03 - Verify problem user can login")
    public void problemUserCanLogin() {
        LoginPage loginPage = openLoginPage();
        loginPage.login(LoginData.userName[2], LoginData.password);
        waitForProductsPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test(testName = "LP_TC_04 - Verify performance glitch user can login")
    public void performanceGlitchUserCanLogin() {
        LoginPage loginPage = openLoginPage();
        loginPage.login(LoginData.userName[3], LoginData.password);
        waitForProductsPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test(testName = "LP_TC_05 - Verify error user can login")
    public void errorUserCanLogin() {
        LoginPage loginPage = openLoginPage();
        loginPage.login(LoginData.userName[4], LoginData.password);
        waitForProductsPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test(testName = "LP_TC_06 - Verify visual user can login")
    public void visualUserCanLogin() {
        LoginPage loginPage = openLoginPage();
        loginPage.login(LoginData.userName[5], LoginData.password);
        waitForProductsPage();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }
}
