package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Usuario;

public class MainApp extends Application {
	
	private static Usuario user;

    public static Usuario getUser() { return user; }

    public static void setUser(Usuario usuario) { user = usuario; }
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/main/view/login.fxml"));
        stage.setTitle("Greenfood - LogIn");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) { launch(); }
}
