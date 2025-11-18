package test;

import baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class MainTestReport extends BaseTest {

    @Test
    public void completeFlowTest() {

        test.info("Launching Login Page...");
        driver.get("https://java-test-haven.lovable.app/login");

        LoginPage lp = new LoginPage(driver);
        HomePage hp = new HomePage(driver);

        test.info("Performing Login...");
        lp.login("admin", "password");

        Assert.assertTrue(driver.getCurrentUrl().contains("home"));
        test.pass("Login Successful!");

        test.info("Navigating to Test Page 1...");
        hp.goToTestPage1();
        Assert.assertTrue(driver.getCurrentUrl().contains("alerts"));
        test.pass("Arrived at Test Page 1!");

        test.info("Go back to Home...");
        hp.goHome();
        Assert.assertTrue(driver.getCurrentUrl().contains("home"));

        test.info("Navigating to Test Page 2...");
        hp.goToTestPage2();
        Assert.assertTrue(driver.getCurrentUrl().contains("forms"));
        test.pass("Arrived at Test Page 2!");

        test.info("Logging Out...");
        hp.logout();
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
        test.pass("Logout Successful!");

        test.info("Test Completed!");
    }
}
