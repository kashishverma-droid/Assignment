package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginBtn = By.id("login-btn");

    private By successToast = By.xpath("//*[contains(text(),'Login Successful')]");
    private By invalidToast = By.xpath("//*[contains(text(),'Invalid') or contains(text(),'incorrect')]");

    public void login(String user, String pass) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(user);

        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(pass);

        driver.findElement(loginBtn).click();
    }

    public void assertValidLogin(com.aventstack.extentreports.ExtentTest test) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
            test.log(Status.PASS, "Login success toast detected");
        } catch (TimeoutException e) {
            test.log(Status.FAIL, "Login Success Toast NOT visible");
            throw new AssertionError("Valid login failed - success toast missing");
        }
    }

    public void assertInvalidLogin(com.aventstack.extentreports.ExtentTest test) {

        boolean successToastVisible = false;
        boolean invalidToastVisible = false;

        try {
            successToastVisible = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(successToast))
                    .isDisplayed();
        } catch (Exception ignored) {}

        try {
            invalidToastVisible = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(invalidToast))
                    .isDisplayed();
        } catch (Exception ignored) {}

        if (successToastVisible) {
            test.log(Status.FAIL, "BUG: Wrong login showed SUCCESS toast!");
            throw new AssertionError("Wrong credentials triggered success login toast!");
        }

        if (!invalidToastVisible) {
            test.log(Status.FAIL, "Invalid login toast did NOT appear!");
            throw new AssertionError("Invalid login toast was NOT displayed!");
        }

        test.log(Status.PASS, "Invalid login validation passed");
    }
}
