package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tn.esprit.models.Produit;
import tn.esprit.services.ServiceProduit;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ProduitMainController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private FlowPane produitCardContainer;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> filterCriteriaComboBox;
    @FXML
    private ComboBox<String> sortOrderComboBox;

    private final ServiceProduit serviceProduit = new ServiceProduit();
    private boolean isAscending = true; // Tri croissant par défaut

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialisation des options de filtrage
        filterCriteriaComboBox.setItems(FXCollections.observableArrayList("Nom", "Prix", "Catégorie", "Quantité"));
        filterCriteriaComboBox.setValue("Nom");

        // Initialisation des options de tri
        sortOrderComboBox.setItems(FXCollections.observableArrayList("Croissant", "Décroissant"));
        sortOrderComboBox.setValue("Croissant");

        // Ajout des écouteurs
        filterCriteriaComboBox.setOnAction(event -> updateProduits());
        sortOrderComboBox.setOnAction(event -> updateProduits());
        searchField.textProperty().addListener((observable, oldValue, newValue) -> updateProduits());

        // Charger et afficher les produits
        updateProduits();
    }

    private void updateProduits() {
        produitCardContainer.getChildren().clear();
        List<Produit> produits = serviceProduit.getAll();

        // 🔍 Filtrage
        String searchText = searchField.getText().toLowerCase();
        String filterCriteria = filterCriteriaComboBox.getValue();
        List<Produit> filteredProduits = produits.stream()
                .filter(produit -> {
                    if ("Nom".equals(filterCriteria)) {
                        return produit.getNomProduit().toLowerCase().contains(searchText);
                    } else if ("Prix".equals(filterCriteria)) {
                        return String.valueOf(produit.getPrixProduit()).contains(searchText);
                    } else if ("Catégorie".equals(filterCriteria)) {
                        return produit.getCategorie().name().toLowerCase().contains(searchText);
                    } else if ("Quantité".equals(filterCriteria)) {
                        return String.valueOf(produit.getQuantite()).contains(searchText);
                    }
                    return true;
                })
                .collect(Collectors.toList());

        // 🔄 Tri
        isAscending = "Croissant".equals(sortOrderComboBox.getValue());
        Comparator<Produit> comparator = switch (filterCriteria) {
            case "Prix" -> Comparator.comparingDouble(Produit::getPrixProduit);
            case "Quantité" -> Comparator.comparingInt(Produit::getQuantite);
            case "Catégorie" -> Comparator.comparing(produit -> produit.getCategorie().name(), String.CASE_INSENSITIVE_ORDER);
            default -> Comparator.comparing(Produit::getNomProduit, String.CASE_INSENSITIVE_ORDER);
        };

        filteredProduits.sort(isAscending ? comparator : comparator.reversed());

        // Affichage
        for (Produit produit : filteredProduits) {
            produitCardContainer.getChildren().add(createProduitCard(produit));
        }
    }

    // Créer une carte de produit
    private VBox createProduitCard(Produit produit) {
        VBox card = new VBox();
        card.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-border-radius: 10px; "
                + "-fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 2);"
                + "-fx-min-width: 200px; -fx-max-width: 200px; -fx-alignment: center; -fx-spacing: 10;");

        Label title = new Label(produit.getNomProduit());
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label prix = new Label("💰 Prix: " + produit.getPrixProduit() + " DT");
        Label description = new Label("📝 " + produit.getDescription());
        Label categorie = new Label("📦 Catégorie: " + produit.getCategorie().name());
        Label quantite = new Label("🔢 Quantité: " + produit.getQuantite());
        Label fournisseur = new Label("🏢 Fournisseur: " + (produit.getFournisseur() != null ? produit.getFournisseur().getNomFournisseur() : "Non défini"));

        Button detailsButton = new Button("Voir Détails");
        detailsButton.setOnAction(e -> showProduitDetails(produit));

        Button modifyButton = new Button("Modifier");
        modifyButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white;");
        modifyButton.setOnAction(e -> goToModifierProduit(produit, e));

        Button deleteButton = new Button("Supprimer");
        deleteButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
        deleteButton.setOnAction(e -> deleteProduit(produit));

        card.getChildren().addAll(title, prix, description, categorie, quantite, fournisseur, detailsButton, modifyButton, deleteButton);
        return card;
    }

    // Afficher les détails d'un produit
    private void showProduitDetails(Produit produit) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Détails du Produit");
        alert.setHeaderText(produit.getNomProduit());
        alert.setContentText("🆔 ID : " + produit.getIdProduit() +
                "\n💰 Prix : " + produit.getPrixProduit() + " DT" +
                "\n📝 Description : " + produit.getDescription() +
                "\n📦 Catégorie : " + produit.getCategorie().name() +
                "\n🔢 Quantité : " + produit.getQuantite() +
                "\n🏢 Fournisseur : " + (produit.getFournisseur() != null ? produit.getFournisseur().getNomFournisseur() : "Non défini"));

        alert.showAndWait();
    }

    // Rediriger vers la page de modification d'un produit
    @FXML
    public void goToModifierProduit(Produit produit, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierProduit.fxml"));
            Parent root = loader.load();

            ModifierProduit controller = loader.getController();
            controller.initData(produit);

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Modifier un Produit");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("⚠️ Erreur lors du chargement de ModifierProduit.fxml !");
        }
    }

    // Supprimer un produit
    @FXML
    public void deleteProduit(Produit produit) {
        serviceProduit.delete(produit);
        updateProduits(); // Rafraîchir l'affichage après suppression
    }

    // Retourner à l'accueil
    public void goToAcceuil(ActionEvent actionEvent) {
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

    // Rediriger vers la gestion des produits
    public void goToGestionProduits(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GestionProduits.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Recherche dynamique des produits.
     */
    @FXML
    public void searchProduits() {
        String searchText = searchField.getText().toLowerCase();
        produitCardContainer.getChildren().clear(); // Nettoyer avant de recharger

        List<Produit> produits = serviceProduit.getAll(); // Récupérer tous les produits

        // Filtrer les produits en fonction du texte de recherche (nom ou catégorie)
        List<Produit> filteredProduits = produits.stream()
                .filter(produit ->
                        produit.getNomProduit().toLowerCase().contains(searchText) || // Recherche par nom
                                produit.getCategorie().name().toLowerCase().contains(searchText) // Recherche par catégorie
                )
                .collect(Collectors.toList());

        // Afficher les produits filtrés
        for (Produit produit : filteredProduits) {
            VBox card = createProduitCard(produit);
            produitCardContainer.getChildren().add(card);
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