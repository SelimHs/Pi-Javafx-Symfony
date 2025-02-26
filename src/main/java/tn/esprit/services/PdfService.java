package tn.esprit.services;

import okhttp3.*;
import org.json.JSONObject;
import java.io.IOException;

public class PdfService {

    private static final String API_KEY = "selim.hassani@esprit.tn_7sGsfZw8OXsK0B0ACUDF7S1GqLNzKPf9P8syuyQlnx6dRBGIpFiYSf9HLUhv3fbK"; // 🔑 Remplace avec ta clé PDF.co

    public static String generatePdfFromBillet(String billetId, String proprietaire, String event, int prix) {
        String apiUrl = "https://api.pdf.co/v1/pdf/convert/from/html";

        // 📌 HTML stylisé pour le billet
        // 📌 HTML stylisé avec bonne syntaxe
        String htmlContent = "<!DOCTYPE html>\n" +
                "<html lang='fr'>\n" +
                "<head>\n" +
                "    <meta charset='UTF-8'>\n" +
                "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n" +
                "    <title>Billet de Réservation</title>\n" +
                "    <style>\n" +
                "        body { font-family: Arial, sans-serif; background: #f8f8f8; padding: 20px; text-align: center; }\n" +
                "        .ticket-container { background: #ffffff; padding: 20px; border-radius: 12px; \n" +
                "            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1); width: 400px; margin: auto; border: 2px dashed #8a2be2; }\n" +
                "        h2 { color: #8a2be2; font-size: 22px; }\n" +
                "        .info { text-align: left; font-size: 16px; margin: 15px 0; }\n" +
                "        .info span { font-weight: bold; color: #333; }\n" +
                "        .qr-container { text-align: center; margin-top: 15px; }\n" +
                "        .footer { margin-top: 20px; font-size: 12px; color: #666; }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class='ticket-container'>\n" +
                "        <h2>🎟️ Billet de Réservation</h2>\n" +
                "        <p class='info'><span>ID du Billet:</span> " + billetId + "</p>\n" +
                "        <p class='info'><span>Propriétaire:</span> " + proprietaire + "</p>\n" +
                "        <p class='info'><span>Événement:</span> " + event + "</p>\n" +
                "        <p class='info'><span>Prix:</span> " + prix + " DT</p>\n" +
                "        \n" +
                "        <div class='qr-container'>\n" +
                "            <img src='https://api.qrserver.com/v1/create-qr-code/?size=120x120&data=" + billetId + "'>\n" +
                "        </div>\n" +
                "        \n" +
                "        <div class='footer'>Merci d'avoir réservé votre billet ! 🎉</div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";


        // 📌 Création de la requête HTTP
        OkHttpClient client = new OkHttpClient();
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("html", htmlContent);
        jsonBody.put("name", "billet_" + billetId + ".pdf");

        RequestBody body = RequestBody.create(
                jsonBody.toString(),
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url(apiUrl)
                .post(body)
                .addHeader("x-api-key", API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                JSONObject jsonResponse = new JSONObject(response.body().string());
                return jsonResponse.getString("url"); // 📥 Retourne l'URL du PDF généré
            } else {
                System.out.println("❌ Erreur API PDF.co : " + response.body().string());
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
