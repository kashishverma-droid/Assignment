package ElementUtils;

import CustomException.TooManyElementsException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementUtils {

    public static WebElement safeFindElement(WebDriver driver, By locator) {

        List<WebElement> elements = driver.findElements(locator);

        if (elements.size() == 0) {
            throw new NoSuchElementException("No element found for locator: " + locator);
        }

        if (elements.size() > 1) {
            throw new TooManyElementsException(
                    "Expected ONE element but found " + elements.size() + " elements for locator: " + locator);
        }

        return elements.get(0);
    }
}
