package tn.esprit.services;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import tn.esprit.models.Reservation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static tn.esprit.services.ServiceReservation.getUserNameById;

public class ExcelDesignService {

    public static String generateExcelWithStyle(List<Reservation> reservations) {
        if (reservations.isEmpty()) {
            System.out.println("❌ Aucune réservation à exporter.");
            return null;
        }

        String filePath = "reservations.xlsx";

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Réservations");

            // ✅ STYLE TITRE
            Font titleFont = workbook.createFont();
            titleFont.setBold(true);
            titleFont.setFontHeightInPoints((short) 14);
            titleFont.setColor(IndexedColors.WHITE.getIndex());

            CellStyle titleStyle = workbook.createCellStyle();
            titleStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
            titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            titleStyle.setAlignment(HorizontalAlignment.CENTER);
            titleStyle.setFont(titleFont);

            // ✅ STYLE EN-TÊTE
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());

            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setFont(headerFont);

            // ✅ STYLE DONNÉES
            CellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setAlignment(HorizontalAlignment.CENTER);

            // ✅ STYLE STATUT (ROUGE pour ANNULÉ, VERT pour CONFIRMÉ)
            CellStyle confirmedStyle = workbook.createCellStyle();
            confirmedStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
            confirmedStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            CellStyle cancelledStyle = workbook.createCellStyle();
            cancelledStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            cancelledStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // ✅ AJOUT DU TITRE
            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Liste des Réservations");
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2)); // Fusionner les colonnes pour le titre

            // ✅ AJOUT DE L'EN-TÊTE (NOUVELLE COLONNE : "Nom de l'utilisateur")
            Row headerRow = sheet.createRow(1);
            Cell headerCell1 = headerRow.createCell(0);
            headerCell1.setCellValue("Nom de l'utilisateur");
            headerCell1.setCellStyle(headerStyle);

            Cell headerCell2 = headerRow.createCell(1);
            headerCell2.setCellValue("Date de Réservation");
            headerCell2.setCellStyle(headerStyle);

            Cell headerCell3 = headerRow.createCell(2);
            headerCell3.setCellValue("Statut");
            headerCell3.setCellStyle(headerStyle);

            // ✅ AJOUT DES DONNÉES
            int rowIndex = 2;
            for (Reservation res : reservations) {
                Row row = sheet.createRow(rowIndex++);

                Cell userCell = row.createCell(0);
                userCell.setCellValue(getUserNameById(res.getIdUser())); // Fetch user name dynamically

                Cell dateCell = row.createCell(1);
                dateCell.setCellValue(res.getDateReservation().toString());
                dateCell.setCellStyle(dateStyle);

                Cell statutCell = row.createCell(2);
                statutCell.setCellValue(res.getStatut());

                // Appliquer un style selon le statut
                if ("Confirmé".equalsIgnoreCase(res.getStatut())) {
                    statutCell.setCellStyle(confirmedStyle);
                } else if ("Annulé".equalsIgnoreCase(res.getStatut())) {
                    statutCell.setCellStyle(cancelledStyle);
                }
            }

            // ✅ AUTO-ADAPTER LA LARGEUR DES COLONNES
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);

            // ✅ SAUVEGARDER LE FICHIER EXCEL
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                System.out.println("✅ Fichier Excel généré avec style : " + filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return filePath;
    }
}

