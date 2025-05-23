package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.MainApp;
import model.Receptor;
import model.Usuario;

import java.io.IOException;

public class HomeController {
    @FXML
    private Button logoutMenu;
    @FXML
    private Button foodMenu;
    @FXML
    private Button addFoodButton;
    @FXML
    private Button addDonationButton;

    public void initialize() {
        Usuario usuario = MainApp.getUser();

        if (usuario.getTipo().equals(Receptor.TIPO)) {
            addFoodButton.setVisible(false);
            addDonationButton.setVisible(false);
        }
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        MainApp.setUser(null);

        Stage stage = (Stage) logoutMenu.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/main/view/login.fxml"));
        stage.setTitle("Greenfood - LogIn");
        stage.setScene(new Scene(root));
    }

    public void addFood(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) addFoodButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/main/view/CadastroAlimento.fxml"));
        stage.setTitle("Greenfood - AddFood");
        stage.setScene(new Scene(root));
    }

    public void goToFoods(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) foodMenu.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/main/view/MenuAlimentos.fxml"));
        stage.setTitle("Greenfood - FoodList");
        stage.setScene(new Scene(root));
    }

    public void addDonation(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) addDonationButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/main/view/CadastroDoacao.fxml"));
        stage.setTitle("Greenfood - AddDonation");
        stage.setScene(new Scene(root));
    }
}
