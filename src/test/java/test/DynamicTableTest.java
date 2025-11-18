package test;

import baseTest.BaseTest;
import org.testng.annotations.Test;
import pages.DynamicTablePage;
import ExcelUtils.ExcelWriter;

import java.util.ArrayList;
import java.util.List;

public class DynamicTableTest extends BaseTest {

    @Test
    public void extractTableAndWriteToExcel() {

        DynamicTablePage tablePage = new DynamicTablePage(driver);
        driver.get("https://practice.expandtesting.com/dynamic-table");


        // Collect Headers
        List<String> headers = tablePage.getTableHeaders();

        // Collect Data
        List<List<String>> tableData = tablePage.getTableData();

        // Merge Header + Data
        List<List<String>> finalData = new ArrayList<>();
        finalData.add(headers);
        finalData.addAll(tableData);

        // Dynamic path
        String filePath = System.getProperty("user.dir") + "/TestData/DynamicTableData.xlsx";

        // Write Excel
        ExcelWriter.writeExcel(filePath, "WebTable", finalData);
    }
}