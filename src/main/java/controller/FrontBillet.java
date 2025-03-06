package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tn.esprit.models.Billet;
import tn.esprit.models.Remise;
import tn.esprit.models.Reservation;
import tn.esprit.services.ServiceBillet;
import tn.esprit.services.ServiceEvent;
import tn.esprit.models.Event;
import tn.esprit.services.ServiceRemise;
import tn.esprit.services.ServiceReservation;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


public class FrontBillet {
    @FXML
    private ComboBox eventSelection;
    @FXML
    private TextField nomClient;
    @FXML
    private Label billetDescription;
    @FXML
    private ComboBox typeBillet;
    @FXML
    private Button btnAccueil, btnEvenements,btnEspace,btnProduit;

    @FXML
    private TextField prixBillet;

    @FXML
    private TextField codePromo; // Champ pour entrer un code promo
    @FXML
    private double remiseAppliquee = 0.0; // Stocker le pourcentage de la remise




    private int prixFinalBillet = 0;
    private Event selectedEvent;

    @FXML
    public void initialize() {
        applyHoverEffect(btnAccueil);
        applyHoverEffect(btnEvenements);
        applyHoverEffect(btnEspace);
        applyHoverEffect(btnProduit);

        loadEvents();
        typeBillet.setOnAction(event -> updateBilletDescription());
    }

    private void applyHoverEffect(Button button) {
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #F39C12; -fx-text-fill: white; -fx-border-radius: 10px; -fx-padding: 10px 18px;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: #F39C12; -fx-border-radius: 10px; -fx-padding: 10px 18px;"));
    }

    public void goToAcceuil(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FrontAcceuil.fxml"));
            Parent root = loader.load();

            // Récupérer la scène actuelle et changer de vue
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPrixBillet(int prix) {
        prixFinalBillet = prix;
        prixBillet.setText(prix + " DT");
        prixBillet.setDisable(true);
    }

    public void loadEvents() {
        ServiceEvent serviceEvent = new ServiceEvent();
        List<Event> events = serviceEvent.getAll();
        eventSelection.setItems(FXCollections.observableArrayList(events));
    }

    public void setEventSelection(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
        loadEvents();
        eventSelection.setValue(selectedEvent);
        eventSelection.setDisable(true);
    }


    @FXML
    public void createBilletFront(ActionEvent actionEvent) {
        // ✅ Vérification des champs obligatoires
        if (nomClient.getText().isEmpty() || typeBillet.getValue() == null) {
            showAlert("Erreur", "Veuillez remplir tous les champs.");
            return;
        }

        Event selectedEvent = (Event) eventSelection.getSelectionModel().getSelectedItem();
        if (selectedEvent == null) {
            showAlert("Erreur", "Veuillez sélectionner un événement.");
            return;
        }

        // ✅ Stocker les informations nécessaires pour créer le billet après paiement
        String proprietaire = nomClient.getText();
        int prix = (int) (selectedEvent.getPrix() * (1 - (remiseAppliquee / 100)));
        Billet.TypeBillet type = Billet.TypeBillet.valueOf(typeBillet.getValue().toString());

        // ✅ Aller à la page de paiement en envoyant le prix et les détails du billet
        goToPaymentPage(actionEvent, prix, proprietaire, type, selectedEvent);
    }

    /**
     * 🔥 Redirige vers la page de paiement avec les détails du billet à créer après paiement
     */
    private void goToPaymentPage(ActionEvent actionEvent, int prix, String proprietaire, Billet.TypeBillet type, Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/payment.fxml"));
            Parent root = loader.load();

            // ✅ Récupérer le contrôleur de paiement et lui envoyer les données du billet
            MainController paymentController = loader.getController();
            paymentController.setPaymentDetails(prix, proprietaire, type, event, this); // 🔥 Passer "this" pour rappel après paiement

            // ✅ Afficher la page de paiement
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible d'ouvrir la page de paiement.");
        }
    }
    public void createBilletAfterPayment(String proprietaire, int prix, Billet.TypeBillet type, Event event) {
        ServiceBillet sb = new ServiceBillet();
        BilletsMainController billetController = new BilletsMainController();
        ServiceReservation sr = new ServiceReservation();

        int finalPrice = (int) (prix * (1 - (remiseAppliquee / 100)));

        Billet billet = new Billet();
        billet.setProprietaire(proprietaire);
        billet.setPrix(finalPrice);
        billet.setDateAchat(LocalDateTime.now());
        billet.setType(type);
        billet.setEvent(event);

        int billetId = sb.addd(billet);
        if (billetId == -1) {
            showAlert("Erreur", "Impossible d'ajouter le billet après paiement.");
            return;
        }
        billet.setIdBillet(billetId);

        billetController.exportBilletToPdf(billet);

        Reservation reservation = new Reservation();
        reservation.setIdUser(1);
        reservation.setIdEvent(event.getIdEvent());
        reservation.setDateReservation(LocalDateTime.now().toString());
        reservation.setStatut("Confirmé");

        sr.add(reservation);

        showAlert("Réservation réussie !", "Votre billet pour '" + event.getNomEvent() + "' a été créé avec succès !\n"
                + "Réservation confirmée ✅");

        goToEvents(); // ✅ Assure-toi que cette méthode ne prend PAS de ActionEvent !
    }






    /**
     * 🔄 Redirige l'utilisateur vers la page de paiement et transmet le prix du billet.
     */


    private void updateBilletDescription() {
        String selectedType = (String) typeBillet.getValue();
        Event selectedEvent = (Event) eventSelection.getSelectionModel().getSelectedItem();

        if (selectedType != null && selectedEvent != null) {
            int basePrice = selectedEvent.getPrix(); // Base event price
            int calculatedPrice = basePrice; // Default price

            // ✅ Adjust price based on ticket type
            switch (selectedType) {
                case "SIMPLE":
                    billetDescription.setText("✔ Ce billet est valide pour une seule personne.");
                    calculatedPrice = basePrice;
                    break;
                case "DUO":
                    billetDescription.setText("✔ Ce billet est valide pour deux personnes.");
                    calculatedPrice = (int) (basePrice * 1.5);
                    break;
                case "VIP":
                    billetDescription.setText("✔ Ce billet est valide pour quatre personnes.");
                    calculatedPrice = basePrice * 3;
                    break;
                default:
                    billetDescription.setText("");
                    break;
            }

            // ✅ Apply remise if available
            if (remiseAppliquee > 0) {
                int priceAfterDiscount = (int) (calculatedPrice * (1 - (remiseAppliquee / 100)));
                prixBillet.setText(priceAfterDiscount + " DT"); // ✅ Display discounted price
                billetDescription.setText(billetDescription.getText() + " 🔥 Promo appliquée !");
            } else {
                prixBillet.setText(calculatedPrice + " DT"); // ✅ Display price without discount
            }
        }
    }




    public void goToEvents(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/FrontEvents.fxml"));
            Parent root = loader.load();

            // Récupérer la scène actuelle et changer de vue
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void goToEvents() { // ✅ Version sans ActionEvent
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/FrontEvents.fxml"));
            Parent root = loader.load();

            // ✅ Récupérer la scène depuis un élément existant ou une fenêtre active
            Stage stage = (Stage) prixBillet.getScene().getWindow(); // Utiliser prixBillet pour référence

            if (stage == null) {
                System.out.println("Erreur: Impossible de récupérer la fenêtre actuelle !");
                return;
            }

            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible d'ouvrir la liste des événements.");
        }
    }



    public void goToEspaces(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/FrontEspace.fxml"));
            Parent root = loader.load();

            // Récupérer la scène actuelle et changer de vue
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToProduit(ActionEvent actionEvent) {
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontend/FrontProduit.fxml"));
            Parent root = loader.load();

            // Récupérer la scène actuelle et changer de vue
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    // Ajoute ces méthodes dans FrontBillet.java
    public  void setNomClient(String nom) {
        this.nomClient.setText(nom);
    }

    public  void setTypeBillet(Billet.TypeBillet type) {
        this.typeBillet.setValue(type);
    }

    public void applyPromoCode(ActionEvent actionEvent) {
        String codeSaisi = codePromo.getText().trim();

        if (codeSaisi.isEmpty()) {
            showAlert("Erreur", "Veuillez entrer un code promo !");
            return;
        }

        ServiceRemise serviceRemise = new ServiceRemise();
        Remise remise = serviceRemise.getRemiseByCode(codeSaisi); // ✅ Ensure only valid & non-expired remises

        if (remise != null) {
            remiseAppliquee = remise.getPourcentageRemise(); // ✅ Store remise percentage

            // ✅ Automatically update the price after applying remise
            updateBilletDescription();

            showAlert("Succès", "✅ Code promo appliqué ! Vous bénéficiez de " + remiseAppliquee + "% de réduction.");
        } else {
            showAlert("Erreur", "❌ Code promo invalide ou expiré !");
        }
    }
    public int getUpdatedPrixBillet() {
        return Integer.parseInt(prixBillet.getText().replace(" DT", "").trim()); // Get the displayed updated price
    }



}