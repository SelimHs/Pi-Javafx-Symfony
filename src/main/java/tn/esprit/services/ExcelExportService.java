package tn.esprit.services;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import tn.esprit.models.Reservation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ExcelExportService {

    private static final String API_URL = "https://json-to-excel.p.rapidapi.com/"; // ✅ URL correcte
    private static final String API_KEY = "709b7e345fmshd343de8ed8f0f77p14485ajsne28e51f687b5"; // Remplacez par votre clé API RapidAPI

    public static String generateExcelFromReservations(List<Reservation> reservations) {
        if (reservations.isEmpty()) {
            System.out.println("❌ Aucune réservation à exporter.");
            return null;
        }

        // 📌 Conversion en JSON au format attendu par l'API
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("filename", "reservations"); // Nom du fichier

        JSONArray rowsArray = new JSONArray();
        for (Reservation res : reservations) {
            JSONObject row = new JSONObject();
            row.put("Date de réservation", res.getDateReservation().toString());
            row.put("Statut", res.getStatut());
            rowsArray.put(row);
        }

        jsonBody.put("rows", rowsArray);

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(jsonBody.toString(), MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("X-RapidAPI-Host", "json-to-excel.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                JSONObject jsonResponse = new JSONObject(response.body().string());

                // 🟢 Debugging : Afficher la réponse JSON complète
                System.out.println("Réponse complète de l'API : " + jsonResponse.toString(4));

                // 🔍 Vérifier quel champ contient le lien du fichier
                if (jsonResponse.has("file")) {
                    String excelUrl = jsonResponse.getString("file");
                    downloadExcelFile(excelUrl, "reservations.xlsx");
                    return excelUrl;
                } else if (jsonResponse.has("url")) { // L'API peut utiliser un autre champ
                    String excelUrl = jsonResponse.getString("url");
                    downloadExcelFile(excelUrl, "reservations.xlsx");
                    return excelUrl;
                } else {
                    System.err.println("❌ Erreur : Aucun champ 'file' ou 'url' trouvé dans la réponse API.");
                    return null;
                }
            } else {
                System.err.println("❌ Erreur API : " + response.code() + " - " + response.message());
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    // ✅ Téléchargement du fichier Excel
    private static void downloadExcelFile(String fileUrl, String fileName) {
        if (fileUrl.equals("URL_NON_TROUVÉE")) {
            System.err.println("❌ Impossible de télécharger : L'API n'a pas fourni d'URL.");
            return;
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(fileUrl).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                Files.write(Paths.get(fileName), response.body().bytes());
                System.out.println("✅ Fichier Excel téléchargé : " + fileName);
            } else {
                System.err.println("❌ Erreur de téléchargement : " + response.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
