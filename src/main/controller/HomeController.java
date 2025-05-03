package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import main.MainApp;
import usuario.Usuario;

public class HomeController {
    @FXML
    private Label welcomeLabel;

    public void initialize() {
        Usuario usuario = MainApp.getUser();
        welcomeLabel.setText("Bem vindo, " + usuario.getNome());
    }
}
