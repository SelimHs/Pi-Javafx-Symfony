package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.models.Espace;
import tn.esprit.services.ServiceEspace;

import javafx.scene.control.Button;
import javafx.scene.paint.Color; // Import the Color class

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

public class GestionEspace {

    @FXML
    private TextField nomEspace, adresseEspace, capaciteEspace, prixEspace, typeEspace;
    @FXML
    private ComboBox<String> disponibiliteEspace;


    private final ServiceEspace serviceEspace = new ServiceEspace();

    @FXML
    public void initialize() {
        disponibiliteEspace.setValue("Disponible"); // Définit "Disponible" par défaut
    }


    @FXML private ImageView espaceImageView;
    private String imagePath = null;



    @FXML
    public void choisirImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                // Générer un nom unique pour éviter les conflits
                String uniqueName = UUID.randomUUID() + "_" + selectedFile.getName();
                String targetDir = "C:/wamp64/www/img/";

                // Copier l'image vers le dossier cible
                Files.copy(
                        selectedFile.toPath(),
                        Paths.get(targetDir + uniqueName),
                        StandardCopyOption.REPLACE_EXISTING
                );

                // Stocker le chemin complet dans imagePath
                imagePath = targetDir + uniqueName;

                // Afficher l'image dans l'ImageView
                espaceImageView.setImage(new Image(new FileInputStream(imagePath)));

            } catch (IOException e) {
                afficherAlerte("Erreur", "Impossible de charger l'image : " + e.getMessage());
            }
        }
    }


    // 🔹 Navigation vers l'affichage des espaces
    @FXML
    public void afficherEspaces(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/AfficherEspaces.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // 🔹 Retour à la page d'ajout
    @FXML
    public void ajouterEspaceView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GestionEspace.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // 🔹 Ajout d'un nouvel espace avec contrôle de saisie
    @FXML
    public void ajouterEspace(ActionEvent event) {
        try {
            // 🛑 Vérification des champs vides
            if (nomEspace.getText().isEmpty() || adresseEspace.getText().isEmpty() ||
                    capaciteEspace.getText().isEmpty() || prixEspace.getText().isEmpty() ||
                    typeEspace.getText().isEmpty() || disponibiliteEspace.getValue() == null) {
                afficherAlerte("Erreur", "Veuillez remplir tous les champs !");
                return;
            }

            // 🛑 Vérification des valeurs numériques
            int capacite;
            float prix;
            try {
                capacite = Integer.parseInt(capaciteEspace.getText());
                if (capacite <= 0) {
                    afficherAlerte("Erreur", "La capacité doit être un nombre positif.");
                    return;
                }
            } catch (NumberFormatException e) {
                afficherAlerte("Erreur", "La capacité doit être un nombre entier.");
                return;
            }

            try {
                prix = Float.parseFloat(prixEspace.getText());
                if (prix <= 0) {
                    afficherAlerte("Erreur", "Le prix doit être un nombre positif.");
                    return;
                }
            } catch (NumberFormatException e) {
                afficherAlerte("Erreur", "Le prix doit être un nombre décimal.");
                return;
            }

            if (imagePath == null) {
                afficherAlerte("Erreur", "Veuillez sélectionner une image !");
                return;
            }

            Espace espace = new Espace();
            espace.setNomEspace(nomEspace.getText());
            espace.setAdresse(adresseEspace.getText());
            espace.setCapacite(capacite);
            espace.setPrix(prix);
            espace.setTypeEspace(typeEspace.getText());
            espace.setDisponibilite(disponibiliteEspace.getValue());
            espace.setIdUser(1);
            espace.setImage(imagePath); // Chemin absolu stocké en BD


            // 🔹 Ajout dans la base de données
            serviceEspace.add(Optional.of(espace));

            // ✅ Confirmation
            afficherAlerte("Succès", "L'espace a été ajouté avec succès !");

            // 🗑 Réinitialisation des champs
            viderChamps();

        } catch (Exception e) {
            afficherAlerte("Erreur", "Une erreur s'est produite lors de l'ajout : " + e.getMessage());
        }
    }

    // 📌 Fonction pour afficher des alertes
    private void afficherAlerte(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // 📌 Fonction pour vider les champs après un ajout réussi
    private void viderChamps() {
        nomEspace.clear();
        adresseEspace.clear();
        capaciteEspace.clear();
        prixEspace.clear();
        typeEspace.clear();
        disponibiliteEspace.getSelectionModel().clearSelection();
        espaceImageView.setImage(null);
        imagePath = null;

    }

    @FXML
    private void retourAccueil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Acceuil.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Erreur lors du chargement de Acceuil.fxml");
        }
    }

    @FXML
    public void buttonHoverEffect(javafx.scene.input.MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: #8e44ad; -fx-text-fill: white; -fx-padding: 18px; -fx-border-width: 2px; -fx-border-color: white;");
        DropShadow shadow = new DropShadow();
        shadow.setRadius(10);
        shadow.setOffsetX(0);
        shadow.setOffsetY(5);
        shadow.setColor(Color.web("#a868a0", 0.7));  // Une ombre douce
        btn.setEffect(shadow);
    }

    @FXML
    public void buttonExitEffect(javafx.scene.input.MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #a868a0;-fx-font-size: 18px; -fx-border-radius: 10px; -fx-padding: 10px 18px;");
        btn.setEffect(null);
    }
}