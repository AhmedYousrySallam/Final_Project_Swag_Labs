package Swag;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;
    private final String url = "https://www.saucedemo.com/";

    private final By userNameLocator = By.id("user-name");
    private final By passwordLocator = By.name("password");
    private final By loginButtonLocator = By.id("login-button");
    private final By errorMessageLocator = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(url);
    }

    public void enterUsername(String username) {
        driver.findElement(userNameLocator).clear();
        driver.findElement(userNameLocator).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordLocator).clear();
        driver.findElement(passwordLocator).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButtonLocator).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessageLocator).getText();
    }

    public boolean isLoginButtonDisplayed() {
        return driver.findElement(loginButtonLocator).isDisplayed();
    }
}
