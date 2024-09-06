package com.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nimatullah
 */
public class MainProcessor {

    private static final String VALID_OUTPUT = "Выходные данные/valid_tickets.xlsx";
    private static final String INVALID_OUTPUT = "Выходные данные/invalid_tickets.xlsx";

    public static void main(String[] args) {
        try (InputStream inputStream = MainProcessor.class.getClassLoader().getResourceAsStream("Входные данные/Реестр.xlsx");
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            List<Row> validRows = new ArrayList<>();
            List<Row> invalidRows = new ArrayList<>();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;  // Пропустить заголовок

                if (DataFilter.isRowValid(row)) {
                    validRows.add(row);
                } else {
                    invalidRows.add(row);
                }
            }

            saveToExcel(VALID_OUTPUT, validRows);
            saveToExcel(INVALID_OUTPUT, invalidRows);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveToExcel(String path, List<Row> rows) {
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fos = new FileOutputStream(path)) {

            Sheet sheet = workbook.createSheet("Tickets");
            for (int i = 0; i < rows.size(); i++) {
                Row newRow = sheet.createRow(i);
                Row originalRow = rows.get(i);
                for (int j = 0; j < originalRow.getPhysicalNumberOfCells(); j++) {
                    Cell newCell = newRow.createCell(j);
                    Cell oldCell = originalRow.getCell(j);
                    if (oldCell != null) {
                        newCell.setCellValue(oldCell.toString());
                    }
                }
            }
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
