package tn.esprit.services;

import okhttp3.*;
import org.json.JSONObject;
import tn.esprit.models.Billet;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class PdfService {

    private static final String API_KEY = "selim.hassani@esprit.tn_7sGsfZw8OXsK0B0ACUDF7S1GqLNzKPf9P8syuyQlnx6dRBGIpFiYSf9HLUhv3fbK"; // 🔑 Remplace avec ta clé PDF.co

    public static String generatePdfFromBillet(Billet billet, String proprietaire, String event, int prix) {

        // ✅ Encode event details in QR code as JSON
        JSONObject qrJson = new JSONObject();
        qrJson.put("Nom event", billet.getEvent().getNomEvent()); // ✅ Corrected field name
        qrJson.put("Proprietaire", proprietaire); // ✅ Added owner
        qrJson.put("Date", billet.getEvent().getDate().toString());
        qrJson.put("Adresse", billet.getEvent().getNomEspace());
        qrJson.put("Type billet", billet.getType().toString());
        qrJson.put("Prix", billet.getPrix());

        // ✅ Generate a properly encoded local server URL
        String encodedQrData = URLEncoder.encode(qrJson.toString(), StandardCharsets.UTF_8);
        String doubleEncodedQrData = URLEncoder.encode(encodedQrData, StandardCharsets.UTF_8); // Extra encoding
        String localServerUrl = "http://172.18.6.174:5000/event?data=" + doubleEncodedQrData;

        // ✅ Generate QR Code URL
        String qrCodeUrl = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + localServerUrl;

        String apiUrl = "https://api.pdf.co/v1/pdf/convert/from/html";

        // ✅ Updated HTML content with QR Code
        String htmlContent = "<!DOCTYPE html>\n" +
                "<html lang='fr'>\n" +
                "<head>\n" +
                "    <meta charset='UTF-8'>\n" +
                "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n" +
                "    <title>Billet de Réservation</title>\n" +
                "    <style>\n" +
                "        body { font-family: 'Arial', sans-serif; background: #f4f4f4; padding: 20px; text-align: center; }\n" +
                "        .ticket { background: white; width: 420px; margin: auto; padding: 20px;\n" +
                "                  border-radius: 15px; box-shadow: 0px 5px 20px rgba(0, 0, 0, 0.2);\n" +
                "                  border: 3px solid #2c3e50; position: relative; overflow: hidden; }\n" +
                "        .header { background: #2c3e50; color: white; padding: 12px; text-transform: uppercase;\n" +
                "                  font-size: 20px; font-weight: bold; letter-spacing: 2px; }\n" +
                "        .content { text-align: left; padding: 15px; }\n" +
                "        .content p { font-size: 16px; margin: 8px 0; }\n" +
                "        .content span { font-weight: bold; color: #2c3e50; }\n" +
                "        .divider { border-top: 2px dashed #2c3e50; margin: 15px 0; }\n" +
                "        .qr-section { display: flex; align-items: center; justify-content: center;\n" +
                "                     padding: 10px; }\n" +
                "        .qr-section img { width: 120px; height: 120px; }\n" +
                "        .badge { background: #e74c3c; color: white; padding: 5px 10px; font-size: 14px;\n" +
                "                  border-radius: 5px; text-transform: uppercase; font-weight: bold; }\n" +
                "        .footer { font-size: 12px; color: #666; margin-top: 10px; }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class='ticket'>\n" +
                "        <div class='header'>🎫 Billet Électronique</div>\n" +
                "        <div class='content'>\n" +
                "            <p><span>Nom :</span> " + proprietaire + "</p>\n" +
                "            <p><span>Événement :</span> " + event + "</p>\n" +
                "            <p><span>Prix :</span> " + billet.getPrix() + " DT</p>\n" +
                "            <p class='badge'>Valide uniquement pour cet événement</p>\n" +
                "        </div>\n" +
                "        <div class='divider'></div>\n" +
                "        <div class='qr-section'>\n" +
                "            <img src='" + qrCodeUrl + "'>\n" +
                "        </div>\n" +
                "        <div class='footer'>Merci pour votre réservation 🎉</div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";

        // ✅ Create HTTP Request to generate PDF
        OkHttpClient client = new OkHttpClient();
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("html", htmlContent);
        jsonBody.put("name", "billet_" + proprietaire + ".pdf");

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
                return jsonResponse.getString("url"); // 📥 Return the generated PDF URL
            } else {
                System.out.println("❌ Error from PDF.co API: " + response.body().string());
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
