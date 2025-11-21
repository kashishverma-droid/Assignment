package test;

import baseTest.BaseTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import java.time.Duration;

public class MainTestReport extends BaseTest {

    @Test
    public void fullAutomationTest() {

        test = extent.createTest("Full Automation Test - Login");

        SoftAssert soft = new SoftAssert();
        WebDriver driver = this.driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        LoginPage login = new LoginPage(driver);

        try {

            // VALID LOGIN
            driver.get("https://java-test-haven.lovable.app/login");
            test.log(Status.INFO, "Opened Login Page");

            login.login("admin", "admin123");

            try {
                login.assertValidLogin(test);
                test.log(Status.PASS, "Valid login passed");
            } catch (AssertionError ae) {
                soft.fail(ae.getMessage());
                test.log(Status.FAIL, "Valid login failed: " + ae.getMessage());
            }

            // INVALID LOGIN
            driver.get("https://java-test-haven.lovable.app/login");
            test.log(Status.INFO, "Testing INVALID login");

            login.login("testuser", "password");  // wrong credentials

            try {
                login.assertInvalidLogin(test);
                test.log(Status.PASS, "Invalid login correctly handled");
            } catch (AssertionError ae) {
                soft.fail(ae.getMessage());
                test.log(Status.FAIL, "Invalid login FAILED: " + ae.getMessage());
            }

        } catch (Exception e) {
            test.log(Status.FAIL, "Test crashed: " + e.getMessage());
            e.printStackTrace();
        }

        soft.assertAll();
    }
}
