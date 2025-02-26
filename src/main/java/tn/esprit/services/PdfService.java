package tn.esprit.services;

import okhttp3.*;
import org.json.JSONObject;
import java.io.IOException;

public class PdfService {

    private static final String API_KEY = "selim.hassani@esprit.tn_7sGsfZw8OXsK0B0ACUDF7S1GqLNzKPf9P8syuyQlnx6dRBGIpFiYSf9HLUhv3fbK"; // 🔑 Remplacez par votre clé PDF.co

    public static String generatePdfFromBillet(String billetId, String proprietaire, String event, int prix) {
        String apiUrl = "https://api.pdf.co/v1/pdf/convert/from/html";

        // 📌 Contenu HTML du billet
        String htmlContent = "<html><body>" +
                "<h2 style='color:blue;'>Billet de Réservation</h2>" +
                "<p><strong>ID du Billet :</strong> " + billetId + "</p>" +
                "<p><strong>Propriétaire :</strong> " + proprietaire + "</p>" +
                "<p><strong>Événement :</strong> " + event + "</p>" +
                "<p><strong>Prix :</strong> " + prix + " DT</p>" +
                "</body></html>";

        // Création de la requête HTTP
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
            if (response.isSuccessful()) {
                JSONObject jsonResponse = new JSONObject(response.body().string());
                return jsonResponse.getString("url"); // Retourne l’URL du PDF généré
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
