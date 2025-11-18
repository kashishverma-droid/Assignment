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

}
