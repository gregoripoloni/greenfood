package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.MainApp;
import model.Alimento;
import model.Receptor;
import model.Usuario;
import persistence.DoadorDAO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class HomeController {
    @FXML
    private Button logoutMenu;
    @FXML
    private Button foodMenu;
    @FXML
    private Button donationMenu;
    @FXML
    private Button addFoodButton;
    @FXML
    private Button addDonationButton;
    @FXML
    private VBox containerAlimentos;


    public void initialize() {
        Usuario usuario = MainApp.getUser();

        if (usuario.getTipo().equals(Receptor.TIPO)) {
            addFoodButton.setVisible(false);
            addDonationButton.setVisible(false);
        }
        
        List<Alimento> todos = DoadorDAO.recuperarTodosAlimentos();
        for (Alimento a : todos) {
            if (a.getValidade().isBefore(LocalDate.now())) {
                Label info = new Label(a.getNome() + " (Vencido em: " + a.getValidade() + ")");
                containerAlimentos.getChildren().add(info);
            }
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

    public void goToDonations(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) donationMenu.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/main/view/ConsultaDoacoes.fxml"));
        stage.setTitle("Greenfood - DonationList");
        stage.setScene(new Scene(root));
    }
}
