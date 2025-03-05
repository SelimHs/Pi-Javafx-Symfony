package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tn.esprit.models.Billet;
import tn.esprit.services.PdfService;
import tn.esprit.services.ServiceBillet;

import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import java.awt.*;
import java.net.URI;

public class BilletsMainController {

    ServiceBillet sb = new ServiceBillet();

    ObservableList<Billet> billetNames = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    private ListView billetListView;
    @FXML
    private TextField searchField;
    @javafx.fxml.FXML
    private Button modifierBouton;
    @javafx.fxml.FXML
    private Button deleteBouton;

    @FXML
    private ComboBox<String> filterCriteriaComboBox; // Pour le filtrage
    @FXML
    private ComboBox<String> sortOrderComboBox; // Pour le tri

    @FXML
    public void initialize() {
        // Initialisation des ComboBox et autres éléments
        filterCriteriaComboBox.setItems(FXCollections.observableArrayList(
                "Propriétaire", "Date d'achat", "Prix", "Type", "Événement"
        ));
        filterCriteriaComboBox.setValue("Propriétaire");

        sortOrderComboBox.setItems(FXCollections.observableArrayList(
                "Croissant", "Décroissant"
        ));
        sortOrderComboBox.setValue("Croissant");

        // Ajouter des écouteurs
        filterCriteriaComboBox.setOnAction(event -> applyFilterAndSort());
        sortOrderComboBox.setOnAction(event -> applyFilterAndSort());
        searchField.textProperty().addListener((observable, oldValue, newValue) -> applyFilterAndSort());

        // Charger et afficher les billets
        applyFilterAndSort();
    }



    //Here lies my navigation
    @FXML
    public void goToAjoutBillet(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ajoutBillet.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @javafx.fxml.FXML
    public void goToAcceuil(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Acceuil.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showBilletDetails(Billet billet) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DetailBillet.fxml"));
            Parent root = loader.load();

            // ✅ Récupérer le contrôleur et envoyer les données du billet
            DetailBillet controller = loader.getController();
            controller.initData(billet);

            // ✅ Changer la scène pour afficher les détails du billet
            Stage stage = (Stage) billetCardContainer.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void deleteAndRefreshBillet(Billet billet) {
        sb.delete(billet);
        applyFilterAndSort(); // Rafraîchir l'affichage après la suppression
    }


    //here lies my core
    @FXML
    private FlowPane billetCardContainer;
    @javafx.fxml.FXML


    private void applyFilterAndSort() {
        billetCardContainer.getChildren().clear();

        // Récupérer tous les billets
        List<Billet> billets = sb.getAll();

        // 🔍 Filtrage
        String searchText = searchField.getText().toLowerCase();
        String filterCriteria = filterCriteriaComboBox.getValue();

        List<Billet> filteredBillets = billets.stream()
                .filter(billet -> {
                    switch (filterCriteria) {
                        case "Propriétaire":
                            return billet.getProprietaire().toLowerCase().contains(searchText);
                        case "Date d'achat":
                            return billet.getDateAchat().toString().toLowerCase().contains(searchText);
                        case "Prix":
                            return String.valueOf(billet.getPrix()).contains(searchText);
                        case "Type":
                            return billet.getType().toString().toLowerCase().contains(searchText);
                        case "Événement":
                            return billet.getEvent().getNomEvent().toLowerCase().contains(searchText);
                        default:
                            return true; // Aucun filtre appliqué
                    }
                })
                .collect(Collectors.toList());

        // 🔄 Tri
        String sortOrder = sortOrderComboBox.getValue();
        Comparator<Billet> comparator = switch (filterCriteria) {
            case "Propriétaire" -> Comparator.comparing(Billet::getProprietaire, String.CASE_INSENSITIVE_ORDER);
            case "Date d'achat" -> Comparator.comparing(Billet::getDateAchat);
            case "Prix" -> Comparator.comparingDouble(Billet::getPrix);
            case "Type" -> Comparator.comparing(Billet::getType);
            case "Événement" -> Comparator.comparing(billet -> billet.getEvent().getNomEvent(), String.CASE_INSENSITIVE_ORDER);
            default -> Comparator.comparing(Billet::getProprietaire, String.CASE_INSENSITIVE_ORDER); // Par défaut, tri par propriétaire
        };

        // Inverser l'ordre si "Décroissant" est sélectionné
        if ("Décroissant".equals(sortOrder)) {
            comparator = comparator.reversed();
        }

        // Appliquer le tri
        filteredBillets.sort(comparator);

        // Afficher les billets filtrés et triés
        displayFilteredBillets(filteredBillets);
    }



    public void exportBilletToPdf(Billet billet) {
        String eventName = (billet.getEvent() != null) ? billet.getEvent().getNomEvent() : "Événement non défini";
        String pdfUrl = PdfService.generatePdfFromBillet(
                billet,
                billet.getProprietaire(),
                eventName,
                billet.getPrix()
        );


        if (pdfUrl != null) {
            openPdfInBrowser(pdfUrl);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Échec de l'exportation");
            alert.setContentText("Impossible de générer le PDF.");
            alert.showAndWait();
        }
    }
    public static void openPdfInBrowser(String pdfUrl) {
        try {
            Desktop.getDesktop().browse(new URI(pdfUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openEditPopup(Billet billet, Button sourceButton){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifierBillet.fxml"));
            Parent root = loader.load();

            // pass selected event to controller
            ModifierBilletController modifierBilletController = loader.getController();
            modifierBilletController.initDataBillet(billet);

            // replace scene for current stage
            Stage stage = (Stage) sourceButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private void filterBillets(String searchText) {
        billetCardContainer.getChildren().clear();

        List<Billet> filteredBillets = sb.getAll().stream()
                .filter(billet ->
                        billet.getProprietaire().toLowerCase().contains(searchText.toLowerCase()) ||
                                billet.getDateAchat().toString().toLowerCase().contains(searchText.toLowerCase()) ||
                                String.valueOf(billet.getPrix()).contains(searchText) ||
                                billet.getType().toString().toLowerCase().contains(searchText.toLowerCase()) ||
                                billet.getEvent().getNomEvent().toLowerCase().contains(searchText.toLowerCase())
                )
                .toList();


        displayFilteredBillets(filteredBillets);
    }
    private void displayFilteredBillets(List<Billet> billets) {
        billetCardContainer.getChildren().clear(); // Nettoyer l'affichage avant d'ajouter des billets

        for (Billet billet : billets) {
            VBox card = new VBox();
            card.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-border-radius: 10px; "
                    + "-fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 2);"
                    + "-fx-min-width: 220px; -fx-max-width: 220px; -fx-alignment: center; -fx-spacing: 10;");

            Label title = new Label("🎟 Billet de " + billet.getProprietaire());
            title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

            Label eventName = new Label("🎉 " + billet.getEvent().getNomEvent());
            Label price = new Label("💰 " + billet.getPrix() + " DT");
            price.setStyle("-fx-text-fill: #27AE60; -fx-font-weight: bold;");

            // 🔍 Bouton Voir Détails avec icône
            Button detailsButton = new Button();
            detailsButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
            detailsButton.setOnAction(e -> showBilletDetails(billet));

            ImageView detailsIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/details-icon.png")));
            detailsIcon.setFitWidth(18);
            detailsIcon.setFitHeight(18);
            detailsButton.setGraphic(detailsIcon);

            // ✅ Icônes Modifier, Supprimer et Export PDF
            HBox buttonContainer = new HBox(8);
            buttonContainer.setStyle("-fx-alignment: center;");

            Button editButton = new Button();
            editButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
            editButton.setOnAction(e -> openEditPopup(billet, editButton));

            ImageView editIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/edit-icon.png")));
            editIcon.setFitWidth(18);
            editIcon.setFitHeight(18);
            editButton.setGraphic(editIcon);

            Button deleteButton = new Button();
            deleteButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
            deleteButton.setOnAction(e -> deleteAndRefreshBillet(billet));

            ImageView trashIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/trash-icon.png")));
            trashIcon.setFitWidth(18);
            trashIcon.setFitHeight(18);
            deleteButton.setGraphic(trashIcon);

            Button exportPdfButton = new Button();
            exportPdfButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
            exportPdfButton.setOnAction(e -> exportBilletToPdf(billet));

            ImageView pdfIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/pdf-icon.png")));
            pdfIcon.setFitWidth(18);
            pdfIcon.setFitHeight(18);
            exportPdfButton.setGraphic(pdfIcon);

            buttonContainer.getChildren().addAll(editButton, deleteButton, exportPdfButton);
            card.getChildren().addAll(title, eventName, price, detailsButton, buttonContainer);
            billetCardContainer.getChildren().add(card);
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
