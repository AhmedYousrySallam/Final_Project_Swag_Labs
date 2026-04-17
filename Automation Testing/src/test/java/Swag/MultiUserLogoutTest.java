package Swag;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MultiUserLogoutTest extends BaseTest {

    @DataProvider(name = "allUsersForLogout")
    public Object[][] allUsersForLogout() {
        List<String> allowed = new ArrayList<>();
        for (String u : LoginData.userName) {
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

    @Test(dataProvider = "allUsersForLogout", testName = "MU_LO_TC_01 - Logout works for all users")
    public void logoutWorksForAllUsers(String username) {
        loginAs(username);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("inventory.html"));

        LogoutPage logoutPage = new LogoutPage(driver);
        logoutPage.logout();

        Assert.assertTrue(logoutPage.isOnLoginPage(), "User should be navigated back to login page after logout");
    }
}
