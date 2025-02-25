package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tn.esprit.models.Billet;
import tn.esprit.services.ServiceBillet;

import java.io.IOException;
import java.util.List;

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

    //here lies my init
    @FXML
    public void initialize() {

        displayBillets(null);


        searchField.textProperty().addListener((observable, oldValue, newValue) -> filterBillets(newValue));
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

    //Here lies my extras
    private void showBilletDetails(Billet billet) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Détails du Billet");
        alert.setHeaderText("Billet de " + billet.getProprietaire());
        alert.setContentText("📅 Date d'achat : " + billet.getDateAchat() +
                "\n💰 Prix : " + billet.getPrix() + " DT" +
                "\n🎟 Type : " + billet.getType() +
                "\n📍 Événement : " + billet.getEvent());

        alert.showAndWait();
    }


    @FXML
    public void deleteAndRefreshBillet(Billet billet) {
        ServiceBillet se = new ServiceBillet();
        se.delete(billet);
        billetCardContainer.getChildren().clear();
        displayBillets(null);
    }


    //here lies my core
    @FXML
    private FlowPane billetCardContainer;
    @javafx.fxml.FXML


    public void displayBillets(javafx.event.ActionEvent actionEvent) {
        billetCardContainer.getChildren().clear();

        List<Billet> billets = sb.getAll();
        for (Billet billet : billets) {
            VBox card = new VBox();
            card.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-border-radius: 10px; "
                    + "-fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 2);"
                    + "-fx-min-width: 200px; -fx-max-width: 200px; -fx-alignment: center; -fx-spacing: 10;");

            Label title = new Label("Ticket de " + billet.getProprietaire());
            title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

            Label name = new Label(billet.getProprietaire());
            name.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
            Label price = new Label("💰 " + billet.getPrix() + " DT");
            Label eventName = new Label("🎉 " + billet.getEvent());

            Button detailsButton = new Button("Voir Détails");
            detailsButton.setOnAction(b -> showBilletDetails(billet));


            Button deleteButton = new Button("Supprimer");
            deleteButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
            deleteButton.setOnAction(e -> deleteAndRefreshBillet(billet));

            Button editButton = new Button("Modifier");
            editButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white;");
            editButton.setOnAction(e -> openEditPopup(billet,editButton));

            card.getChildren().addAll(title, name, price, eventName, detailsButton,editButton, deleteButton);
            billetCardContainer.getChildren().add(card);

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
        for (Billet billet : billets) {
            VBox card = new VBox();
            card.setStyle("-fx-background-color: white; -fx-padding: 10px; -fx-border-radius: 10px; "
                    + "-fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 2);"
                    + "-fx-min-width: 200px; -fx-max-width: 200px; -fx-alignment: center; -fx-spacing: 10;");

            Label title = new Label("Ticket de " + billet.getProprietaire());
            title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

            Label name = new Label(billet.getProprietaire());
            name.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
            Label price = new Label("💰 " + billet.getPrix() + " DT");
            Label eventName = new Label("🎉 " + billet.getEvent().getNomEvent());

            Button detailsButton = new Button("Voir Détails");
            detailsButton.setOnAction(b -> showBilletDetails(billet));


            Button deleteButton = new Button("Supprimer");
            deleteButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
            deleteButton.setOnAction(e -> deleteAndRefreshBillet(billet));

            Button editButton = new Button("Modifier");
            editButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white;");
            editButton.setOnAction(e -> openEditPopup(billet, editButton));

            card.getChildren().addAll(title, name, price, eventName, detailsButton, editButton, deleteButton);
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
