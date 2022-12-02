package com.sparta.jn;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataDriven {

    ArrayList<String> getData(String testcaseName) {
        XSSFWorkbook workbook = null;
        ArrayList<String> strings = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\kwokl\\Udemy\\" +
                    "Rest API Testing (Automation) from Scratch-Rest Assured Java\\ExcelDrivenTestFramework\\" +
                    "Demo Data.xlsx");
            workbook = new XSSFWorkbook(fileInputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

        int sheetsCount = workbook.getNumberOfSheets();
        for(int i = 0; i < sheetsCount; i++) {
            if(workbook.getSheetName(i).equalsIgnoreCase("testData")) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                Iterator<Row> rows = sheet.iterator();
                Row firstRow = rows.next();
                Iterator<Cell> cell = firstRow.cellIterator();
                int count = 0;
                int coloumn = 0;
                while (cell.hasNext()) {
                    Cell value = cell.next();
                    if(value.getStringCellValue().equalsIgnoreCase("TestCases")) {
                        coloumn = count;
                    }
                    count++;
                }
                System.out.println(coloumn);
                while(rows.hasNext()) {
                    Row r = rows.next();
                    if (r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testcaseName)) {
                        Iterator<Cell> cellValues = r.cellIterator();
                        while (cellValues.hasNext()) {
                            Cell cellValue = cellValues.next();
                            if (cellValue.getCellType() == CellType.STRING) {
                                strings.add(cellValue.getStringCellValue());
                            } else if (cellValue.getCellType() == CellType.NUMERIC) {
                                strings.add(NumberToTextConverter.toText(cellValue.getNumericCellValue()));
                            }
                        }
                    }
                }
            }
        }
        return strings;
    }
}
