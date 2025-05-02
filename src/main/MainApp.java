package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import usuario.Usuario;

import java.io.IOException;

public class MainApp extends Application {

    private static Usuario user;
    private static Stage stage;
    private static Scene loginScene;
    private static Scene homeScene;

    public static void main(String[] args) {
        launch();
    }

    public static Usuario getUser() { return user; }

    public static void setUser(Usuario usuario) { user = usuario; }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setTitle("GreenFood");

        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/main/view/login.fxml"));
        loginScene = new Scene(fxmlLogin, 600, 400);

        Parent fxmlHome = FXMLLoader.load(getClass().getResource("/main/view/home.fxml"));
        homeScene = new Scene(fxmlHome, 600, 400);

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    public static void changeScene(String screen) {
        switch (screen) {
            case "login":
                stage.setScene(loginScene);
                break;
            case "home":
                stage.setScene(homeScene);
        }
    }
}
