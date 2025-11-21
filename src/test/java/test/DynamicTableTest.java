package test;

import baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DynamicTablePage;
import ExcelUtils.ExcelReader;
import ExcelUtils.ExcelWriter;

import java.util.ArrayList;
import java.util.List;

public class DynamicTableTest extends BaseTest {

    @Test
    public void extractTableAndValidateExcel() {

        DynamicTablePage tablePage = new DynamicTablePage(driver);
        driver.get("https://practice.expandtesting.com/dynamic-table");


        List<String> headers = tablePage.getTableHeaders();
        List<List<String>> tableData = tablePage.getTableData();

        List<List<String>> finalData = new ArrayList<>();
        finalData.add(headers);
        finalData.addAll(tableData);


        String filePath = System.getProperty("user.dir") + "/TestData/DynamicTableData.xlsx";
        ExcelWriter.writeExcel(filePath, "WebTable", finalData);


        Object[][] excelData = ExcelReader.getExcelData(filePath, "WebTable");


        Assert.assertEquals(
                excelData.length,
                tableData.size(),
                "Row count mismatch between UI and Excel!"
        );


        for (int i = 0; i < tableData.size(); i++) {

            List<String> uiRow = tableData.get(i);

            Assert.assertEquals(
                    excelData[i].length,
                    uiRow.size(),
                    "Column count mismatch at row: " + i
            );

            for (int j = 0; j < uiRow.size(); j++) {

                String excelValue = excelData[i][j].toString().trim();
                String uiValue = uiRow.get(j).trim();

                Assert.assertEquals(
                        excelValue,
                        uiValue,
                        "Cell mismatch at row " + i + ", column " + j
                );
            }
        }

        System.out.println("Excel data successfully validated with UI dynamic table!");
    }
}
