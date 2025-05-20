package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.MainApp;
import model.Usuario;
import service.UsuarioService;

import java.io.IOException;

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
            UsuarioService usuarioService = new UsuarioService();
            Usuario usuario = usuarioService.cadastrar(nameField.getText(), emailField.getText(), passwordField.getText(), phoneField.getText(), (String) typeField.getValue());
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
