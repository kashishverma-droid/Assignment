package test;

import org.apache.poi.xssf.usermodel.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;

public class ExcelRowProviderTest {

    @DataProvider(name = "rowData")
    public Object[][] getRowData() {

        String path = System.getProperty("user.dir") + "/TestData/DynamicTableData.xlsx";
        String sheet = "WebTable";
        int rowNumber = 2;

        return getSpecificRow(path, sheet, rowNumber);
    }


    public Object[][] getSpecificRow(String path, String sheetName, int rowNumber) {

        try {
            FileInputStream fis = new FileInputStream(path);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet(sheetName);

            int totalRows = sheet.getPhysicalNumberOfRows();
            int totalCols = sheet.getRow(0).getLastCellNum();

            if (rowNumber >= totalRows) {
                throw new RuntimeException("Row " + rowNumber + " does not exist in Excel!");
            }


            XSSFRow row = sheet.getRow(rowNumber);

            Object[][] data = new Object[1][totalCols];

            for (int i = 0; i < totalCols; i++) {
                data[0][i] = row.getCell(i).toString();
            }

            workbook.close();
            return data;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test(dataProvider = "rowData")
    public void printRow(Object[] row) {
        for (Object val : row) {
            System.out.print(val + " | ");
        }
        System.out.println();
    }
}
