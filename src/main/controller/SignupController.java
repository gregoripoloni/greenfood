package main.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import javafx.stage.Stage;

import main.MainApp;
import usuario.CadastroUsuario;
import usuario.Usuario;

public class SignupController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ChoiceBox typeField;
    @FXML
    private Label errorLabel;
    @FXML
    private Button signupButton;
    @FXML
    private Button backButton;

    public void initialize() {
        typeField.getItems().add("doador");
        typeField.getItems().add("receptor");
    }

    public void signUp(ActionEvent actionEvent) {
        try {
            Usuario usuario = CadastroUsuario.iniciar(nameField.getText(), emailField.getText(), passwordField.getText(), phoneField.getText(), (String) typeField.getValue());
            MainApp.setUser(usuario);
            goToHome();
        } catch (Exception e) {
            errorLabel.setText(e.getMessage());
        }
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/main/view/login.fxml"));
        stage.setTitle("Greenfood - LogIn");
        stage.setScene(new Scene(root));
    }

    private void goToHome() throws IOException {
        Stage stage = (Stage) signupButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/main/view/home.fxml"));
        stage.setTitle("Greenfood - Home");
        stage.setScene(new Scene(root));
    }
}
