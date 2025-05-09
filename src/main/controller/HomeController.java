package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.stage.Stage;
import main.MainApp;
import usuario.Doador;
import usuario.Usuario;

import java.io.IOException;

public class HomeController {
    @FXML
    private Button logoutMenu;
    @FXML
    private Button foodButton;
    @FXML
    private Button donationButton;

    public void initialize() {
        Usuario usuario = MainApp.getUser();

        if (usuario.getTipo().equals(Doador.TIPO)) {
            foodButton.setText("Cadastrar alimento");
            donationButton.setText("Cadastrar doação");
        } else {
            foodButton.setVisible(false);
            donationButton.setText("Consultar doações");
        }
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        MainApp.setUser(null);

        Stage stage = (Stage) logoutMenu.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/main/view/login.fxml"));
        stage.setTitle("Greenfood - LogIn");
        stage.setScene(new Scene(root));
    }
}
