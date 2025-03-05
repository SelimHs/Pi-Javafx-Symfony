package tn.esprit.services;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONObject;
import tn.esprit.models.LocalServer;

import java.io.IOException;

public class EventPopup extends Application {

    @Override
    public void start(Stage primaryStage) {
        System.out.println("✅ JavaFX Application Started!");

        // ✅ Start the Local Server in a Separate Thread
        new Thread(() -> {
            try {
                LocalServer.startServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // ✅ Main JavaFX Window
        primaryStage.setTitle("Event Scanner");
        primaryStage.setScene(new Scene(new VBox(new Label("Scan a QR Code to see event details!")), 300, 200));
        primaryStage.show();
    }

    public static void showPopup(String jsonData) {
        Platform.runLater(() -> {
            try {
                System.out.println("🚀 Showing Event Popup...");

                JSONObject eventData = new JSONObject(jsonData);
                String eventName = eventData.getString("Nom évènement");
                String location = eventData.getString("Adresse");
                String ticketType = eventData.getString("Type billet");
                String date = eventData.getString("Date");

                Stage popupStage = new Stage();
                VBox root = new VBox(10);
                root.setStyle("-fx-padding: 20px; -fx-alignment: center; -fx-font-size: 16px;");

                Label titleLabel = new Label("🎉 Event: " + eventName);
                titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
                Label locationLabel = new Label("📍 Location: " + location);
                Label dateLabel = new Label("📅 Date: " + date);
                Label ticketLabel = new Label("🎟️ Ticket Type: " + ticketType);

                root.getChildren().addAll(titleLabel, locationLabel, dateLabel, ticketLabel);
                Scene scene = new Scene(root, 350, 250);
                popupStage.setTitle("New Event Detected!");
                popupStage.setScene(scene);
                popupStage.show();

                System.out.println("✅ JavaFX Popup Opened!");

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
