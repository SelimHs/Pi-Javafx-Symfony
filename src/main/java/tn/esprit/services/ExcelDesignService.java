package tn.esprit.services;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import tn.esprit.models.Reservation;
import tn.esprit.utils.myDatabase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelDesignService {

    public static String generateExcelWithStyle(List<Reservation> reservations) {
        if (reservations.isEmpty()) {
            System.out.println("❌ Aucune réservation à exporter.");
            return null;
        }

        String filePath = "reservations.xlsx";

        // ✅ Fetch all user names once and store in a map (Optimized for performance)
        Map<Integer, String> userNamesMap = getAllUserNames();

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

                // 🔹 Fetch user name from the preloaded map
                Cell userCell = row.createCell(0);
                String userName = userNamesMap.getOrDefault(res.getIdUser(), "Utilisateur inconnu");
                userCell.setCellValue(userName);

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



    public static Map<Integer, String> getAllUserNames() {
        System.out.println("🔵 getAllUserNames() is being called...");

        Map<Integer, String> userNames = new HashMap<>();
        String query = "SELECT idUser, nom FROM user"; // Ensure this matches your DB schema

        try (Connection conn = myDatabase.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("🔍 Fetching all users from the database...");

            boolean hasUsers = false;
            while (rs.next()) {
                hasUsers = true;
                int userId = rs.getInt("idUser");
                String userName = rs.getString("nom");
                userNames.put(userId, userName);

                // 📌 Print user data for debugging
                System.out.println("🟢 User Loaded → ID: " + userId + ", Name: " + userName);
            }

            if (!hasUsers) {
                System.out.println("❌ SQL Query returned no users! Something is wrong.");
                System.out.println("✅ Debug: Are you using the correct table name? (`users`)");
                System.out.println("✅ Debug: Does the `users` table have data?");
                System.out.println("✅ Debug: Try running `SELECT * FROM users` in phpMyAdmin.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ SQL Error: " + e.getMessage());
        }

        return userNames;
    }





}

