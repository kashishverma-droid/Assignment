import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Assig2 {

    public static WebDriver driver;

    public static void main(String[] args)
    {
        driver = new ChromeDriver();
        driver.get("https://practice.expandtesting.com/dynamic-table");
        driver.manage().window().maximize();

        List<WebElement> headers = driver.findElements(By.xpath("//*[@id='core']/div/div/div[2]/div/div[2]/table/thead/tr/th"));

        for (WebElement h : headers) {
            System.out.printf("%-15s", h.getText());
        }
        System.out.println(); // new line


        List<WebElement> rows = driver.findElements(By.xpath("//*[@id='core']/div/div/div[2]/div/div[2]/table/tbody/tr"));
        int rowCount = rows.size();


        int colCount = driver.findElements(By.xpath("//*[@id='core']/div/div/div[2]/div/div[2]/table/tbody/tr[1]/td")).size();


        for (int i = 1; i <= rowCount; i++) {
            for (int j = 1; j <= colCount; j++) {

                String cellXpath = "//*[@id='core']/div/div/div[2]/div/div[2]/table/tbody/tr[" + i + "]/td[" + j + "]";
                WebElement cell = driver.findElement(By.xpath(cellXpath));

                System.out.printf("%-15s", cell.getText());
            }
            System.out.println(); // next row
        }

        driver.quit();
    }
}
