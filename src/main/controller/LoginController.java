package main.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.MainApp;
import usuario.LoginUsuario;
import usuario.Usuario;

public class LoginController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;
    @FXML
    private Button loginButton;
    @FXML
    private Button signupButton;

    public void logIn(ActionEvent actionEvent) {
        try {
            Usuario usuario = LoginUsuario.verificar(emailField.getText(), passwordField.getText());
            MainApp.setUser(usuario);
            goToHome();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            errorLabel.setText(e.getMessage());
        }
    }

    public void signUp(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) signupButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/main/view/signup.fxml"));
        stage.setTitle("Greenfood - SignUp");
        stage.setScene(new Scene(root));
    }

    private void goToHome() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/main/view/home.fxml"));
        stage.setTitle("Greenfood - Home");
        stage.setScene(new Scene(root));
    }
}
