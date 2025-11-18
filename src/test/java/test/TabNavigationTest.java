package test;

import baseTest.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TabNavigationTest extends BaseTest {

    @Test
    public void openAndSwitchTabs() throws InterruptedException {


        driver.get("https://google.com");
        JavascriptExecutor js = (JavascriptExecutor) driver;


        js.executeScript("window.open('https://youtube.com', '_blank');");


        js.executeScript("window.open('https://selenium.dev', '_blank');");


        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        System.out.println("Total tabs opened: " + tabs.size());


        driver.switchTo().window(tabs.get(0));
        System.out.println("Switched to Google");
        Thread.sleep(1500);


        driver.switchTo().window(tabs.get(1));
        System.out.println("Switched to YouTube");
        Thread.sleep(1500);


        driver.switchTo().window(tabs.get(2));
        System.out.println("Switched to Selenium.dev");
        Thread.sleep(1500);
    }
}
