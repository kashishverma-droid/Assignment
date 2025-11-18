package test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ExcelUtils.ExcelReader;

public class ExcelDataProviderTest {

    @DataProvider(name = "excelData")
    public Object[][] getData() {

        String path = System.getProperty("user.dir") + "/TestData/DynamicTableData.xlsx";
        String sheet = "WebTable";

        return ExcelReader.getExcelData(path, sheet);
    }

    @Test(dataProvider = "excelData")
    public void printDynamic(Object[] row) {
        for (Object val : row) {
            System.out.print(val + " | ");
        }
        System.out.println();
    }

}
