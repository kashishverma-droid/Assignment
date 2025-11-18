package test;

import baseTest.BaseTest;
import CustomException.TooManyElementsException;
import org.testng.annotations.Test;
import pages.Alert;

public class TooManyElementsTest extends BaseTest {

    @Test
    public void testTooManyButtons() {

        driver.get("https://java-test-haven.lovable.app/alerts");

        Alert page = new Alert(driver);

        try {
            page.getSingleButton();  // should throw TooManyElementsException
        } catch (TooManyElementsException e) {
            System.out.println("CUSTOM EXCEPTION THROWN!");
            System.out.println(e.getMessage());
        }
    }
}
