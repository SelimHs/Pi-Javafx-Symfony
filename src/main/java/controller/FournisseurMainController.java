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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    // Créer une carte de fournisseur avec des icônes
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

        // 📌 Conteneur horizontal pour les icônes
        HBox buttonContainer = new HBox(8);
        buttonContainer.setStyle("-fx-alignment: center;");

        // 🔍 Icône Voir Détails
        Button detailsButton = new Button();
        detailsButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        detailsButton.setOnAction(e -> showFournisseurDetails(f));

        ImageView detailsIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/details-icon.png")));
        detailsIcon.setFitWidth(18);
        detailsIcon.setFitHeight(18);
        detailsButton.setGraphic(detailsIcon);


        // ✏️ Icône Modifier
        Button modifyButton = new Button();
        modifyButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        modifyButton.setOnAction(e -> goToModifierFournisseur(f, e));

        ImageView editIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/edit-icon.png")));
        editIcon.setFitWidth(18);
        editIcon.setFitHeight(18);
        modifyButton.setGraphic(editIcon);

        // 🗑️ Icône Supprimer
        Button deleteButton = new Button();
        deleteButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        deleteButton.setOnAction(e -> deleteFournisseur(f));

        ImageView trashIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/trash-icon.png")));
        trashIcon.setFitWidth(18);
        trashIcon.setFitHeight(18);
        deleteButton.setGraphic(trashIcon);

        // Ajout des icônes au conteneur
        buttonContainer.getChildren().addAll(detailsButton, modifyButton, deleteButton);

        // Ajout des éléments à la carte
        card.getChildren().addAll(nom, desc, type, tel, buttonContainer);
        return card;
    }


    private void showFournisseurDetails(fournisseur f) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DetailFournisseur.fxml"));
            Parent root = loader.load();

            // ✅ Récupérer le contrôleur et passer les données du fournisseur
            DetailFournisseur controller = loader.getController();
            controller.initData(f);

            // ✅ Afficher dans une nouvelle fenêtre
            Stage stage = new Stage();
            stage.setTitle("Détails du Fournisseur");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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