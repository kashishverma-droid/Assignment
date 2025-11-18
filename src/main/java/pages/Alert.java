package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ElementUtils.ElementUtils;
import org.openqa.selenium.WebElement;

public class Alert {

    private WebDriver driver;

    public Alert(WebDriver driver) {
        this.driver = driver;
    }

    public void alertBtn() {
        driver.findElement(By.id("alert--btn")).click();
        driver.switchTo().alert().accept();
    }

    public void confirmBtn() {
        driver.findElement(By.id("confirm-btn")).click();
        driver.switchTo().alert().accept();
    }

    public void promptBtn() {
        driver.findElement(By.id("prompt-btn")).click();
        org.openqa.selenium.Alert promptAlert = driver.switchTo().alert();
        promptAlert.sendKeys("kashish");
        promptAlert.accept();
    }

    private By allButtons = By.xpath("//button");

    public WebElement getSingleButton() {
        return ElementUtils.safeFindElement(driver, allButtons);
    }
}
