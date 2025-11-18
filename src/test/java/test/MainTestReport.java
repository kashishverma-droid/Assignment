package test;

import baseTest.BaseTest;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;
import pages.Alert;
import pages.Login;

public class MainTestReport extends BaseTest {

    @Test
    public void fullAutomationTest() {


        test = extent.createTest("Full Automation Test - Login Alerts");

        try {

            driver.get("https://java-test-haven.lovable.app/login");
            test.log(Status.INFO, "Opened Login Page");

            Login login = new Login(driver);

            login.enterUsername("testuser");
            login.enterPassword("password");
            login.clickLoginButton();


            test.log(Status.PASS, "Login test passed");

            driver.get("https://java-test-haven.lovable.app/login");



            driver.get("https://java-test-haven.lovable.app/alerts");
            test.log(Status.INFO, "Opened Alerts Page");

            Alert alert = new Alert(driver);

            alert.alertBtn();
            test.log(Status.PASS, "Alert popup handled");

            alert.confirmBtn();
            test.log(Status.PASS, "Confirm popup handled");

            alert.promptBtn();
            test.log(Status.PASS, "Prompt popup handled");

            test.log(Status.PASS, "All alert tests passed");

        } catch (Exception e) {

            test.log(Status.FAIL, "Test Failed: " + e.getMessage());
            throw e;
        }
    }
}
