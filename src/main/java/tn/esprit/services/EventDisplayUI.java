package tn.esprit.services;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class EventDisplayUI extends Application {

    private Label countdownLabel;
    private LocalDateTime eventDateTime;

    @Override
    public void start(Stage primaryStage) {
        // 📌 Lire les données du fichier JSON
        JSONObject eventData = readEventData();

        if (eventData == null) {
            System.out.println("❌ Aucune donnée trouvée !");
            return;
        }

        // 📌 Extraire les infos
        String eventName = eventData.getString("name");
        String eventLocation = eventData.getString("location");
        String eventDateStr = eventData.getString("date");
        int eventPrice = eventData.getInt("price");

        // Convertir en LocalDateTime
        eventDateTime = LocalDateTime.parse(eventDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // 📌 UI Stylisée
        Label title = new Label("🎟 " + eventName);
        title.setFont(Font.font("Arial", 24));
        title.setTextFill(Color.web("#2c3e50"));

        Label locationLabel = new Label("📍 Lieu : " + eventLocation);
        Label dateLabel = new Label("📅 Date : " + eventDateStr);
        Label priceLabel = new Label("💸 Prix : " + eventPrice + " DT");

        countdownLabel = new Label();
        countdownLabel.setFont(Font.font("Arial", 20));
        countdownLabel.setTextFill(Color.RED);

        // 📌 Layout
        VBox root = new VBox(15, title, locationLabel, dateLabel, priceLabel, countdownLabel);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 20px; -fx-background-color: #f4f4f4; -fx-border-color: #2c3e50; -fx-border-width: 2px; -fx-border-radius: 10px;");

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Détails de l'Événement");
        primaryStage.setScene(scene);
        primaryStage.show();

        // 📌 Lancer le compte à rebours
        startCountdown();
    }

    private JSONObject readEventData() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("event_data.json")));
            return new JSONObject(content);
        } catch (Exception e) {
            return null;
        }
    }

    private void startCountdown() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalDateTime now = LocalDateTime.now();
            long secondsLeft = ChronoUnit.SECONDS.between(now, eventDateTime);

            if (secondsLeft > 0) {
                long days = secondsLeft / (24 * 3600);
                long hours = (secondsLeft % (24 * 3600)) / 3600;
                long minutes = (secondsLeft % 3600) / 60;
                long seconds = secondsLeft % 60;

                countdownLabel.setText(String.format("⏳ Décompte : %dj %dh %dm %ds", days, hours, minutes, seconds));
            } else {
                countdownLabel.setText("🎉 L'événement a commencé !");
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
