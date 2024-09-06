package com.example;

import org.apache.poi.ss.usermodel.Cell;

/**
 * @author nimatullah
 */
public class DocumentProcessor {

    public static boolean processDocument(Cell documentTypeCell, Cell amountCell) {
        String documentType = documentTypeCell.getStringCellValue();
        if ("ПЕР_СБ".equals(documentType)) {
            double amount = amountCell.getNumericCellValue();
            amount = applyVatDiscount(amount);
            return isAmountValid(amount);
        }
        return true;
    }

    public static double applyVatDiscount(double amount) {
        return amount * 0.83;  // Вычитание НДС 17%
    }

    public static boolean isAmountValid(double amount) {
        return amount % 250 == 0 || amount % 100 == 0;
    }

    public static boolean isDocumentValid(Cell documentNumberCell) {
        // Логика преобразования номеров документов
        String documentNumber = documentNumberCell.getStringCellValue();
        if (documentNumber.startsWith("ЗАЯВКАФ")) {
            documentNumber = documentNumber.replaceFirst("ЗАЯВКАФ", "Ф67\\90-4321");
            documentNumberCell.setCellValue(documentNumber);
            return true;
        }
        switch (documentNumber) {
            case "ЛГТ":
                documentNumberCell.setCellValue("ЛЬГОТА");
                break;
            case "ВХТ":
                documentNumberCell.setCellValue("ВАХТА");
                break;
            case "ДС":
                documentNumberCell.setCellValue("ДОП СОГЛ");
                break;
            default:
                return false;
        }
        return true;
    }
}
