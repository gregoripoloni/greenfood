package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import main.MainApp;
import usuario.LoginUsuario;
import usuario.Usuario;

public class LoginController {
    @FXML
    private TextField email;
    @FXML
    private PasswordField senha;
    @FXML
    private Label erro;

    public void entrar(ActionEvent actionEvent) {
        checkLogin();
    }

    private void checkLogin() {
        try {
            Usuario usuario = LoginUsuario.verificar(email.getText(), senha.getText());
            usuario.exibirInformacoes();

            MainApp.setUser(usuario);
            MainApp.changeScene("home");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            erro.setText(e.getMessage());
        }
    }
}
