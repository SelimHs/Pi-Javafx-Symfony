package tn.esprit.controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import java.time.LocalTime;

public class Clock extends Pane {
    private Circle face; // Cadran de l'horloge
    private Line hourHand; // Aiguille des heures
    private Line minuteHand; // Aiguille des minutes
    private Line secondHand; // Aiguille des secondes

    public Clock() {
        // Taille de l'horloge
        double size = 150; // Taille du cadran
        double centerX = size / 2;
        double centerY = size / 2;

        // Créer le cadran de l'horloge avec un dégradé
        Stop[] stops = new Stop[] {
                new Stop(0, Color.web("#2c3e50")), // Couleur de départ
                new Stop(1, Color.web("#3498db"))  // Couleur d'arrivée
        };
        LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);

        face = new Circle(centerX, centerY, size / 2 - 10, gradient);
        face.setStroke(Color.WHITE);
        face.setStrokeWidth(5);

        // Ajouter des marques pour les heures
        for (int i = 0; i < 12; i++) {
            double angle = i * 30; // 360° / 12 heures
            double startX = centerX + Math.cos(Math.toRadians(angle)) * (size / 2 - 20);
            double startY = centerY + Math.sin(Math.toRadians(angle)) * (size / 2 - 20);
            double endX = centerX + Math.cos(Math.toRadians(angle)) * (size / 2 - 10);
            double endY = centerY + Math.sin(Math.toRadians(angle)) * (size / 2 - 10);

            Line mark = new Line(startX, startY, endX, endY);
            mark.setStroke(Color.WHITE);
            mark.setStrokeWidth(2);
            getChildren().add(mark);
        }

        // Créer les aiguilles
        hourHand = new Line(centerX, centerY, centerX, centerY - 40);
        hourHand.setStroke(Color.WHITE);
        hourHand.setStrokeWidth(4);

        minuteHand = new Line(centerX, centerY, centerX, centerY - 60);
        minuteHand.setStroke(Color.WHITE);
        minuteHand.setStrokeWidth(3);

        secondHand = new Line(centerX, centerY, centerX, centerY - 70);
        secondHand.setStroke(Color.RED);
        secondHand.setStrokeWidth(2);

        // Ajouter les éléments à l'horloge
        getChildren().addAll(face, hourHand, minuteHand, secondHand);

        // Mettre à jour l'horloge toutes les secondes
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> updateClock())
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    // Méthode pour mettre à jour les aiguilles de l'horloge
    private void updateClock() {
        LocalTime now = LocalTime.now();

        // Calculer les angles des aiguilles
        double secondAngle = now.getSecond() * 6; // 360° / 60 secondes
        double minuteAngle = now.getMinute() * 6 + now.getSecond() * 0.1; // 360° / 60 minutes
        double hourAngle = now.getHour() % 12 * 30 + now.getMinute() * 0.5; // 360° / 12 heures

        // Mettre à jour les aiguilles
        secondHand.setRotate(secondAngle);
        minuteHand.setRotate(minuteAngle);
        hourHand.setRotate(hourAngle);
    }
}