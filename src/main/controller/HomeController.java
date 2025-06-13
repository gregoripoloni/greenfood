package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.MainApp;
import model.Alimento;
import model.Doador;
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
    private Button reportMenu;
    @FXML
    private Button addFoodButton;
    @FXML
    private Button addDonationButton;
    @FXML
    private VBox containerAlimentos;
    @FXML
	private VBox vboxAlimentos;
    
    LocalDate hoje = LocalDate.now();
    LocalDate limite = hoje.plusDays(5);
    
    private VBox criarCardAlimentoVencido(Alimento alimento) {
        VBox card = new VBox();
        card.setSpacing(5);
        card.setPadding(new Insets(10));
        card.setStyle("-fx-border-color: #d9534f; -fx-border-width: 2; -fx-background-color: #f9d6d5; -fx-background-radius: 8;");

        Label nome = new Label("🍽 Alimento: " + alimento.getNome());
        nome.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");

        Label validade = new Label("Vencido em: " + alimento.getValidade());
        validade.setStyle("-fx-text-fill: #a94442;");

        card.getChildren().addAll(nome, validade);
        return card;
    }

  
    public void initialize() {
        Usuario usuario = MainApp.getUser();

        if (usuario.getTipo().equals(Receptor.TIPO)) {
            addFoodButton.setVisible(false);
            addDonationButton.setVisible(false);
        }
        
        List<Alimento> todos = DoadorDAO.recuperarTodosAlimentos();
        
        for (Alimento a : todos) {
            LocalDate validade = a.getValidade();
            if (validade.isBefore(hoje) || (!validade.isAfter(limite))) {
                VBox card = criarCardAlimentoVencido(a);
                containerAlimentos.getChildren().add(card);
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

    public void goToReport(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) reportMenu.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/main/view/RelatorioDoacoes.fxml"));
        stage.setTitle("Greenfood - Report");
        stage.setScene(new Scene(root));
    }
}
