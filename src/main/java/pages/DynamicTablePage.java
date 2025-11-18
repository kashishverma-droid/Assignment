package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class DynamicTablePage {

    WebDriver driver;

    By tableHeaders = By.xpath("//*[@id='core']//table/thead/tr/th");
    By tableRows = By.xpath("//*[@id='core']//table/tbody/tr");

    public DynamicTablePage(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getTableHeaders() {
        List<WebElement> headers = driver.findElements(tableHeaders);
        List<String> headerText = new ArrayList<>();

        for (WebElement h : headers) {
            headerText.add(h.getText());
        }
        return headerText;
    }

    public List<List<String>> getTableData() {

        List<List<String>> table = new ArrayList<>();

        List<WebElement> rows = driver.findElements(tableRows);

        for (int i = 1; i <= rows.size(); i++) {

            List<String> rowData = new ArrayList<>();

            List<WebElement> cols =
                    driver.findElements(By.xpath("//*[@id='core']//table/tbody/tr[" + i + "]/td"));

            for (WebElement c : cols) {
                rowData.add(c.getText());
            }

            table.add(rowData);
        }

        return table;
    }
}
