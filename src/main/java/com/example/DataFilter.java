package com.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author nimatullah
 */
public class DataFilter {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static boolean isRowValid(Row row) {
        try {
            Cell dateCell = row.getCell(1);  // Период сделки
            Cell documentTypeCell = row.getCell(2);  // Тип документа
            Cell amountCell = row.getCell(3);  // Сумма билета
            Cell documentNumberCell = row.getCell(4);  // Номер документа
            Cell nameCell = row.getCell(5);  // ФИО

            return isWithinPeriod(dateCell) &&
                    isDocumentPresent(documentTypeCell) &&
                    isNonZeroAmount(amountCell) &&
                    DocumentProcessor.processDocument(documentTypeCell, amountCell) &&
                    isNamePresent(nameCell) &&
                    DocumentProcessor.isDocumentValid(documentNumberCell);
        } catch (Exception e) {
            return false;  // Любая ошибка означает невалидность строки
        }
    }

    private static boolean isWithinPeriod(Cell dateCell) throws ParseException {
        Date transactionDate = dateCell.getDateCellValue();
        Date startDate = dateFormat.parse("01.03.2024");
        Date endDate = dateFormat.parse("31.03.2024");
        return !transactionDate.before(startDate) && !transactionDate.after(endDate);
    }

    private static boolean isDocumentPresent(Cell documentTypeCell) {
        return documentTypeCell != null && documentTypeCell.getCellType() == CellType.STRING;
    }

    private static boolean isNonZeroAmount(Cell amountCell) {
        return amountCell != null && amountCell.getNumericCellValue() > 0;
    }

    private static boolean isNamePresent(Cell nameCell) {
        return nameCell != null && nameCell.getCellType() == CellType.STRING && !nameCell.getStringCellValue().isEmpty();
    }
}
