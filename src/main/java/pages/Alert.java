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

    // Your required locator:
    private By allButtons = By.xpath("//button");

    public WebElement getSingleButton() {
        return ElementUtils.safeFindElement(driver, allButtons);
    }
}
