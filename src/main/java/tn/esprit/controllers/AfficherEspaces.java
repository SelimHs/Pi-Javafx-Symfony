package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tn.esprit.models.Espace;
import tn.esprit.services.ServiceEspace;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AfficherEspaces {

    @FXML
    private FlowPane espaceCardContainer;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> filterCriteriaComboBox;
    @FXML
    private ComboBox<String> sortOrderComboBox;

    private final ServiceEspace serviceEspace = new ServiceEspace();

    @FXML
    public void initialize() {
        // Initialisation des options de filtrage
        filterCriteriaComboBox.setItems(FXCollections.observableArrayList("Nom", "Adresse", "Type"));
        filterCriteriaComboBox.setValue("Nom"); // Valeur par défaut

        // Initialisation des options de tri
        sortOrderComboBox.setItems(FXCollections.observableArrayList("Croissant", "Décroissant"));
        sortOrderComboBox.setValue("Croissant"); // Valeur par défaut

        // Ajout des écouteurs
        filterCriteriaComboBox.setOnAction(event -> applyFilter());
        sortOrderComboBox.setOnAction(event -> applyFilter());
        searchField.textProperty().addListener((observable, oldValue, newValue) -> applyFilter());

        // Charger et afficher les espaces
        applyFilter();
    }

    private void applyFilter() {
        espaceCardContainer.getChildren().clear();

        // Récupérer les espaces et extraire les objets Espace des Optional
        List<Espace> espaces = serviceEspace.getAll().stream()
                .filter(Optional::isPresent) // Filtrer les Optional non vides
                .map(Optional::get) // Extraire les objets Espace
                .collect(Collectors.toList());

        // 🔍 Filtrage
        String searchText = searchField.getText().toLowerCase();
        List<Espace> filteredEspaces = espaces.stream()
                .filter(espace -> {
                    // Vérifier si le texte de recherche correspond à n'importe quel attribut
                    return espace.getNomEspace().toLowerCase().contains(searchText) || // Nom
                            espace.getAdresse().toLowerCase().contains(searchText) || // Adresse
                            espace.getTypeEspace().toLowerCase().contains(searchText) || // Type
                            String.valueOf(espace.getCapacite()).contains(searchText) || // Capacité
                            espace.getDisponibilite().toLowerCase().contains(searchText) || // Disponibilité
                            String.valueOf(espace.getPrix()).contains(searchText); // Prix
                })
                .collect(Collectors.toList());

        // 🔄 Tri
        boolean isAscending = "Croissant".equals(sortOrderComboBox.getValue());
        String filterCriteria = filterCriteriaComboBox.getValue();
        Comparator<Espace> comparator = switch (filterCriteria) {
            case "Nom" -> Comparator.comparing(Espace::getNomEspace, String.CASE_INSENSITIVE_ORDER);
            case "Adresse" -> Comparator.comparing(Espace::getAdresse, String.CASE_INSENSITIVE_ORDER);
            case "Type" -> Comparator.comparing(Espace::getTypeEspace, String.CASE_INSENSITIVE_ORDER);
            case "Capacité" -> Comparator.comparingInt(Espace::getCapacite);
            case "Prix" -> Comparator.comparingDouble(Espace::getPrix);
            default -> Comparator.comparing(Espace::getNomEspace, String.CASE_INSENSITIVE_ORDER); // Par défaut, tri par nom
        };

        // Appliquer le tri
        filteredEspaces.sort(isAscending ? comparator : comparator.reversed());

        // Affichage
        for (Espace espace : filteredEspaces) {
            espaceCardContainer.getChildren().add(creerCarteEspace(espace));
        }
    }

    private VBox creerCarteEspace(Espace espace) {
        VBox card = new VBox();
        card.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-border-radius: 10px; "
                + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.2), 10, 0, 2, 2); -fx-spacing: 8px;");

        Button btnModifier = new Button("Modifier");
        btnModifier.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white;");
        btnModifier.setOnAction(e -> modifierEspace(espace));

        Button btnSupprimer = new Button("Supprimer");
        btnSupprimer.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
        btnSupprimer.setOnAction(e -> supprimerEspace(espace));

        Button btnDetails = new Button("Détails");
        btnDetails.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
        btnDetails.setOnAction(e -> afficherDetailsEspace(espace, e));

        card.getChildren().addAll(
                new Label("🏠 " + espace.getNomEspace()),
                new Label("📍 " + espace.getAdresse()),
                new Label("👥 Capacité: " + espace.getCapacite()),
                new Label("📅 Disponibilité: " + espace.getDisponibilite()),
                new Label("💰 Prix: " + espace.getPrix() + " DT"),
                new Label("🏢 Type: " + espace.getTypeEspace()),
                btnModifier, btnSupprimer, btnDetails
        );

        return card;
    }

    private void supprimerEspace(Espace espace) {
        serviceEspace.delete(espace.getIdEspace());
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "L'espace a été supprimé avec succès !");
        alert.show();
        applyFilter(); // Rafraîchir l'affichage après suppression
    }

    private void modifierEspace(Espace espace) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierEspace.fxml"));
            Parent root = loader.load();
            ModifierEspace controller = loader.getController();
            controller.initData(espace);

            Stage stage = (Stage) espaceCardContainer.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void afficherDetailsEspace(Espace espace, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DetailEspace.fxml"));
            Parent root = loader.load();

            DetailEspace controller = loader.getController();
            controller.initData(espace);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void ajouterEspaceView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GestionEspace.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void retourAjouterEspace(ActionEvent event) throws IOException {
        ajouterEspaceView(event);
    }

    @FXML
    public void buttonHoverEffect(MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: #8e44ad; -fx-text-fill: white; -fx-padding: 18px; -fx-border-width: 2px; -fx-border-color: white;");
        DropShadow shadow = new DropShadow();
        shadow.setRadius(10);
        shadow.setOffsetX(0);
        shadow.setOffsetY(5);
        shadow.setColor(Color.web("#a868a0", 0.7));
        btn.setEffect(shadow);
    }

    @FXML
    public void buttonExitEffect(MouseEvent mouseEvent) {
        Button btn = (Button) mouseEvent.getSource();
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #a868a0;-fx-font-size: 18px; -fx-border-radius: 10px; -fx-padding: 10px 18px;");
        btn.setEffect(null);
    }
}