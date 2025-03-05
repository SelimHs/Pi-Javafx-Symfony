package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Users;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
public class DetailUser {

    @FXML
    private Label titleLabel;
    @FXML
    private Label userNomLabel;
    @FXML
    private Label userPrenomLabel;
    @FXML
    private Label userEmailLabel;
    @FXML
    private Label userTelephoneLabel;
    @FXML
    private Label userAdresseLabel;
    @FXML
    private Label userTypeLabel;
    @FXML
    private Label userGenreLabel;
    @FXML
    private ImageView userImage;
    @FXML
    private Button retourButton;

    private Users selectedUser;

    public void initData(Users user) {
        this.selectedUser = user;
        titleLabel.setText("Détails de : " + user.getNom() + " " + user.getPrenom());
        userNomLabel.setText("👤 Nom : " + user.getNom());
        userPrenomLabel.setText("📛 Prénom : " + user.getPrenom());
        userEmailLabel.setText("📧 Email : " + user.getEmail());
        userTelephoneLabel.setText("📞 Téléphone : " + user.getNumeroTelephone());
        userAdresseLabel.setText("📍 Adresse : " + user.getAdresse());
        userTypeLabel.setText("🔖 Type : " + user.getType());
        userGenreLabel.setText("⚥ Genre : " + user.getGenre());

    }

    @FXML
    private void retourUtilisateurs(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/homePage.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void retourAccueil(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Acceuil.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
