package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.models.Espace;
import tn.esprit.services.ServiceEspace;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class ModifierEspace {

    @FXML
    private TextField nomEspace, adresseEspace, capaciteEspace, prixEspace, typeEspace;
    @FXML
    private ComboBox<String> disponibiliteEspace;
    @FXML
    private Button btnModifier, btnRetour, btnChoisirImage;
    @FXML
    private ImageView espaceImage;

    private final ServiceEspace serviceEspace = new ServiceEspace();
    private Espace espaceActuel;
    private String imagePath; // Stocke le chemin de l'image sélectionnée

    public void initData(Espace espace) {
        this.espaceActuel = espace;

        nomEspace.setText(espace.getNomEspace());
        adresseEspace.setText(espace.getAdresse());
        capaciteEspace.setText(String.valueOf(espace.getCapacite()));
        prixEspace.setText(String.valueOf(espace.getPrix()));
        typeEspace.setText(espace.getTypeEspace());
        disponibiliteEspace.setValue(espace.getDisponibilite());

        // 📌 Vérifier si une image est stockée en base et existe
        if (espace.getImage() != null && !espace.getImage().isEmpty()) {
            File file = new File(espace.getImage());
            if (file.exists()) {
                espaceImage.setImage(new Image(file.toURI().toString()));
            } else {
                espaceImage.setImage(new Image(getClass().getResourceAsStream("/images/espace-placeholder.jpg")));
            }
        } else {
            espaceImage.setImage(new Image(getClass().getResourceAsStream("/images/espace-placeholder.jpg")));
        }
    }

    @FXML
    public void modifierEspace(ActionEvent event) {
        if (espaceActuel == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Aucun espace sélectionné !");
            alert.show();
            return;
        }

        try {
            espaceActuel.setNomEspace(nomEspace.getText());
            espaceActuel.setAdresse(adresseEspace.getText());
            espaceActuel.setCapacite(Integer.parseInt(capaciteEspace.getText()));
            espaceActuel.setPrix(Float.parseFloat(prixEspace.getText()));
            espaceActuel.setTypeEspace(typeEspace.getText());
            espaceActuel.setDisponibilite(disponibiliteEspace.getValue());

            // 📌 Vérifier si une nouvelle image a été choisie, sinon garder l'ancienne
            if (imagePath != null) {
                espaceActuel.setImage(imagePath);
            } else {
                espaceActuel.setImage(espaceActuel.getImage()); // Garde l'image existante
            }

            serviceEspace.update(Optional.ofNullable(espaceActuel));

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "L'espace a été modifié avec succès !");
            alert.show();

            retourAfficherEspaces(event);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez entrer des valeurs valides.");
            alert.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void choisirImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

        if (selectedFile != null) {
            try {
                // 📌 Définir le dossier de stockage des images
                String targetDir = "C:/wamp64/www/img/";
                File dir = new File(targetDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // 📌 Éviter les conflits de nom en ajoutant un timestamp
                String fileName = System.currentTimeMillis() + "_" + selectedFile.getName();
                File destination = new File(targetDir + fileName);

                // 📌 Copier l'image sélectionnée dans le dossier cible
                Files.copy(selectedFile.toPath(), destination.toPath());

                imagePath = destination.getAbsolutePath(); // Mise à jour du chemin de l'image
                espaceImage.setImage(new Image(destination.toURI().toString()));

            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur lors du téléchargement de l'image.");
                alert.show();
            }
        }
    }

    @FXML
    public void retourAfficherEspaces(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/AfficherEspaces.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
