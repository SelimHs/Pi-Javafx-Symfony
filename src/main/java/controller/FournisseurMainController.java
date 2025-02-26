package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tn.esprit.models.fournisseur;
import tn.esprit.services.ServiceFournisseur;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FournisseurMainController implements Initializable {

    @FXML
    private FlowPane fournisseurCardContainer;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> filterCriteriaComboBox;
    @FXML
    private ComboBox<String> sortOrderComboBox;

    private final ServiceFournisseur fournisseurService = new ServiceFournisseur();
    private boolean isAscending = true; // Tri croissant par défaut

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialisation des options de filtrage
        filterCriteriaComboBox.setItems(FXCollections.observableArrayList("Nom", "Type", "Téléphone"));
        filterCriteriaComboBox.setValue("Nom");

        // Initialisation des options de tri
        sortOrderComboBox.setItems(FXCollections.observableArrayList("Croissant", "Décroissant"));
        sortOrderComboBox.setValue("Croissant");

        // Ajout des écouteurs
        filterCriteriaComboBox.setOnAction(event -> updateFournisseurs());
        sortOrderComboBox.setOnAction(event -> updateFournisseurs());
        searchField.textProperty().addListener((observable, oldValue, newValue) -> updateFournisseurs());

        // Charger et afficher les fournisseurs
        updateFournisseurs();
    }

    private void updateFournisseurs() {
        fournisseurCardContainer.getChildren().clear();
        List<fournisseur> fournisseurs = fournisseurService.getAll();

        // 🔍 Filtrage
        String searchText = searchField.getText().toLowerCase();
        String filterCriteria = filterCriteriaComboBox.getValue();
        List<fournisseur> filteredFournisseurs = fournisseurs.stream()
                .filter(f -> {
                    if ("Nom".equals(filterCriteria)) {
                        return f.getNomFournisseur().toLowerCase().contains(searchText);
                    } else if ("Type".equals(filterCriteria)) {
                        return f.getType().toLowerCase().contains(searchText);
                    } else if ("Téléphone".equals(filterCriteria)) {
                        return f.getTelephone().toLowerCase().contains(searchText);
                    }
                    return true;
                })
                .collect(Collectors.toList());

        // 🔄 Tri
        isAscending = "Croissant".equals(sortOrderComboBox.getValue());
        Comparator<fournisseur> comparator = switch (filterCriteria) {
            case "Type" -> Comparator.comparing(fournisseur::getType, String.CASE_INSENSITIVE_ORDER);
            case "Téléphone" -> Comparator.comparing(fournisseur::getTelephone, String.CASE_INSENSITIVE_ORDER);
            default -> Comparator.comparing(fournisseur::getNomFournisseur, String.CASE_INSENSITIVE_ORDER);
        };

        filteredFournisseurs.sort(isAscending ? comparator : comparator.reversed());

        // Affichage
        for (fournisseur f : filteredFournisseurs) {
            fournisseurCardContainer.getChildren().add(createFournisseurCard(f));
        }
    }

    // Créer une carte de fournisseur
    private VBox createFournisseurCard(fournisseur f) {
        VBox card = new VBox();
        card.setStyle("-fx-background-color: rgba(255, 255, 255, 0.85); " +
                "-fx-padding: 15px; " +
                "-fx-border-radius: 12px; " +
                "-fx-background-radius: 12px; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 3, 3); " +
                "-fx-min-width: 230px; -fx-max-width: 230px; -fx-alignment: center; -fx-spacing: 12;");

        // 🏢 Nom du fournisseur en gras
        Label nom = new Label("🏢 " + f.getNomFournisseur());
        nom.setStyle("-fx-font-size: 18px; -fx-text-fill: #2C3E50; -fx-font-weight: bold;");

        // 📝 Description
        Label desc = new Label("📝 " + f.getDescription());
        desc.setStyle("-fx-font-size: 14px; -fx-text-fill: #34495E;");

        // 🏷 Type
        Label type = new Label("🏷 " + f.getType());
        type.setStyle("-fx-font-size: 14px; -fx-text-fill: #34495E;");

        // 📞 Téléphone avec une couleur distincte
        Label tel = new Label("📞 " + f.getTelephone());
        tel.setStyle("-fx-font-size: 14px; -fx-text-fill: #2980B9;");

        // 📌 Conteneur horizontal pour les boutons
        HBox buttonContainer = new HBox(8);
        buttonContainer.setStyle("-fx-alignment: center;");

        // 👁️ Bouton Voir Détails
        Button detailsButton = new Button("👁️ Voir Détails");
        detailsButton.setStyle("-fx-background-color: #BDC3C7; -fx-text-fill: black; -fx-border-radius: 8px;");
        detailsButton.setOnAction(e -> showFournisseurDetails(f));

        // ✏️ Bouton Modifier
        Button modifyButton = new Button("✏ Modifier");
        modifyButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-border-radius: 8px;");
        modifyButton.setOnAction(e -> goToModifierFournisseur(f, e));

        // 🗑️ Bouton Supprimer
        Button deleteButton = new Button("🗑 Supprimer");
        deleteButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-border-radius: 8px;");
        deleteButton.setOnAction(e -> deleteFournisseur(f));

        // Ajout des boutons au conteneur
        buttonContainer.getChildren().addAll(detailsButton, modifyButton, deleteButton);

        // Ajout des éléments à la carte
        card.getChildren().addAll(nom, desc, type, tel, buttonContainer);
        return card;
    }

    // Afficher les détails d'un fournisseur
    private void showFournisseurDetails(fournisseur f) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Détails du Fournisseur");
        alert.setHeaderText(f.getNomFournisseur());
        alert.setContentText("🆔 ID : " + f.getIdFournisseur() +
                "\n📝 Description : " + f.getDescription() +
                "\n🏷 Type : " + f.getType() +
                "\n📞 Téléphone : " + f.getTelephone());

        alert.showAndWait();
    }

    // Rediriger vers la page de modification d'un fournisseur
    @FXML
    public void goToModifierFournisseur(fournisseur fournisseur, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierFournisseur.fxml"));
            Parent root = loader.load();

            ModifierFournisseur controller = loader.getController();
            controller.initData(fournisseur);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Modifier un Fournisseur");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("⚠️ Erreur lors du chargement de ModifierFournisseur.fxml !");
        }
    }

    // Supprimer un fournisseur
    @FXML
    public void deleteFournisseur(fournisseur f) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression de Fournisseur");
        alert.setHeaderText("Voulez-vous vraiment supprimer " + f.getNomFournisseur() + " ?");
        alert.setContentText("Cette action est irréversible.");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                fournisseurService.delete(f);
                updateFournisseurs(); // Rafraîchir l'affichage après suppression
            }
        });
    }

    // Retourner à l'accueil
    public void goToAcceuil(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Acceuil.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Rediriger vers la gestion des fournisseurs
    public void goToGestionFournisseur(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gestionFournisseur.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Effet de survol pour les boutons
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

    // Effet de sortie pour les boutons
    @FXML
    public void buttonExitEffect(javafx.scene.input.MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #a868a0;-fx-font-size: 18px; -fx-border-radius: 10px; -fx-padding: 10px 18px;");
        btn.setEffect(null);
    }
}