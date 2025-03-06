package tn.esprit.services;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventDisplay extends Application {

    private static EventDisplay instance;
    private Label countdownLabel = new Label("⏳ Loading countdown...");
    private LocalDateTime eventDateTime;

    @Override
    public void start(Stage primaryStage) {
        instance = this; // Store instance for reusing

        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 20px; -fx-alignment: center; -fx-font-size: 16px;");

        // ✅ Read JSON event data from event_data.json
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get("event_data.json")));
            JSONObject eventData = new JSONObject(jsonContent);

            String eventName = eventData.getString("Nom évènement");
            String eventLocation = eventData.getString("Adresse");
            String ticketType = eventData.getString("Type billet");
            eventDateTime = LocalDateTime.parse(eventData.getString("Date"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            Label titleLabel = new Label("🎉 Event: " + eventName);
            titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

            Label locationLabel = new Label("📍 Location: " + eventLocation);
            Label dateLabel = new Label("📅 Date: " + eventDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
            Label ticketLabel = new Label("🎟️ Ticket Type: " + ticketType);

            root.getChildren().addAll(titleLabel, locationLabel, dateLabel, ticketLabel, countdownLabel);

            // ✅ Start countdown
            startCountdown();

        } catch (IOException e) {
            countdownLabel.setText("❌ Error: Could not read event data.");
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Event Details");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startCountdown() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            java.time.Duration remainingTime = java.time.Duration.between(LocalDateTime.now(), eventDateTime);
            if (remainingTime.isNegative()) {
                countdownLabel.setText("🎊 The event has started!");
            } else {
                long days = remainingTime.toDays();
                long hours = remainingTime.toHours() % 24;
                long minutes = remainingTime.toMinutes() % 60;
                long seconds = remainingTime.getSeconds() % 60;

                countdownLabel.setText("⏳ Starts in: " + days + "d " + hours + "h " + minutes + "m " + seconds + "s");
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void showEvent() {
        if (instance != null) {
            Platform.runLater(() -> instance.start(new Stage())); // Open new window if already running
        } else {
            new Thread(() -> Application.launch(EventDisplay.class)).start(); // Start JavaFX only once
        }
    }
}
