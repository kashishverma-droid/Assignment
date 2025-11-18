package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login {

    private WebDriver driver;
    private WebDriverWait wait;

    public Login(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterUsername(String username) {
        WebElement element = driver.findElement(By.id("username"));
        element.clear();
        element.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement element = driver.findElement(By.id("password"));
        element.clear();
        element.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement element = driver.findElement(By.id("login-btn"));
        element.click();
    }

    public void verifyDashboard() {
        WebElement dashboardMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Authentication Successful')]"))
        );

        if (dashboardMessage.isDisplayed()) {
            System.out.println("Message: " + dashboardMessage.getText());
        } else {
            System.out.println("Dashboard not displayed after login.");
        }
    }

    public void clickLogoutButton() {
        WebElement element = driver.findElement(By.id("logout-btn"));
        element.click();
    }


    public void ForgotPassword() {
        WebElement forgotLink = driver.findElement(By.id("forgot-password-link"));
        forgotLink.click();
    }

    public void ResetPassword() {
        WebElement modalTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h2[contains(text(),'Reset Password')]"))
        );

        if (modalTitle.isDisplayed()) {
            System.out.println("Reset Password modal displayed successfully!");
        } else {
            System.out.println("Reset Password modal not displayed.");
        }
    }

    public void enterResetEmail(String email) {
        WebElement emailInput = driver.findElement(By.id("reset-email"));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void clickSendResetLink() {
        WebElement sendButton = driver.findElement(By.xpath("//button[contains(text(),'Send Reset Link')]"));
        sendButton.click();
    }
}
