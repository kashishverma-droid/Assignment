package ExcelUtils;

import org.apache.poi.xssf.usermodel.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class ExcelWriter {

    public static void writeExcel(String filePath, String sheetName, List<List<String>> data) {

        try {
            File file = new File(filePath);

            // Create directory if missing
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet(sheetName);

            // Write data
            for (int i = 0; i < data.size(); i++) {
                XSSFRow row = sheet.createRow(i);
                List<String> rowData = data.get(i);

                for (int j = 0; j < rowData.size(); j++) {
                    row.createCell(j).setCellValue(rowData.get(j));
                }
            }

            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();

            System.out.println("Excel generated at: " + filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}