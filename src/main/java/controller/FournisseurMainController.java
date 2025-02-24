package controller;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.fournisseur;
import tn.esprit.services.ServiceFournisseur;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FournisseurMainController implements Initializable {

    @FXML
    private FlowPane fournisseurCardContainer;
    @FXML
    private TextField searchField;

    private final ServiceFournisseur fournisseurService = new ServiceFournisseur();

    // Clés Twilio pour l'envoi de SMS
    public static final String ACCOUNT_SID = "ACXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    public static final String AUTH_TOKEN = "your_auth_token";

    /**
     * Méthode appelée automatiquement lors du chargement du contrôleur
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadFournisseurs("");  // Chargement initial des fournisseurs

        // Ajout d'un écouteur sur le champ de recherche
        searchField.textProperty().addListener((observable, oldValue, newValue) -> loadFournisseurs(newValue));
    }

    /**
     * 🔍 Charger et afficher les fournisseurs
     */
    @FXML
    public void loadFournisseurs(String search) {
        fournisseurCardContainer.getChildren().clear();
        List<fournisseur> fournisseurs = fournisseurService.getAll();

        for (fournisseur f : fournisseurs) {
            if (f.getNomFournisseur().toLowerCase().contains(search.toLowerCase())) {
                fournisseurCardContainer.getChildren().add(createFournisseurCard(f));
            }
        }
    }

    /**
     * 🏷️ Créer une carte pour un fournisseur
     */
    private VBox createFournisseurCard(fournisseur f) {
        VBox card = new VBox();
        card.setStyle("-fx-background-color: rgba(255, 255, 255, 0.15); -fx-padding: 15px; -fx-border-radius: 12px; -fx-border-color: white;");
        card.setSpacing(10);

        Label nom = new Label("🏢 " + f.getNomFournisseur());
        nom.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-font-weight: bold;");

        Label desc = new Label("📝 " + f.getDescription());
        desc.setStyle("-fx-font-size: 14px; -fx-text-fill: white;");

        Label type = new Label("🏷 " + f.getType());
        type.setStyle("-fx-font-size: 14px; -fx-text-fill: white;");

        Label tel = new Label("📞 " + f.getTelephone());
        tel.setStyle("-fx-font-size: 14px; -fx-text-fill: white;");

        Button detailsButton = new Button("👁️ Voir Détails");
        detailsButton.setOnAction(e -> showFournisseurDetails(f));

        Button modifyButton = new Button("✏ Modifier");
        modifyButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white;");
        modifyButton.setOnAction(e -> goToModifierFournisseur(f, e));

        Button deleteButton = new Button("🗑 Supprimer");
        deleteButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
        deleteButton.setOnAction(e -> deleteFournisseur(f));



        card.getChildren().addAll(nom, desc, type, tel, detailsButton, modifyButton, deleteButton);
        return card;
    }

    /**
     * ℹ️ Afficher les détails d'un fournisseur
     */
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

    /**
     * 📝 Aller à la page de modification
     */
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

    /**
     * ❌ Supprimer un fournisseur
     */
    @FXML
    public void deleteFournisseur(fournisseur f) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression de Fournisseur");
        alert.setHeaderText("Voulez-vous vraiment supprimer " + f.getNomFournisseur() + " ?");
        alert.setContentText("Cette action est irréversible.");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                fournisseurService.delete(f);
                loadFournisseurs(""); // Rafraîchir la liste
            }
        });
    }

    /**
     * 📩 Envoyer un SMS au fournisseur
     */


    /**
     * 🏠 Aller à l'accueil
     */
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
}
