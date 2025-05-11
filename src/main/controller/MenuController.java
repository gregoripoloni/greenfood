package main.controller;

import java.io.IOException;
import java.util.List;

import alimentos.Alimento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import usuario.PersistenciaDoador;

public class MenuController {
	
	@FXML
	private void initialize() {
		List<Alimento> alimentos = PersistenciaDoador.recuperarTodosAlimentos();
		
		System.out.println("Qnts aliments tem:"+alimentos.size());
    }
	
	 @FXML
	    private void abrirHome(ActionEvent event) {
	    	
	    	try {
	            // Carregar a nova tela (MenuAlimentos.fxml)
	            
	            Parent newRoot = FXMLLoader.load(getClass().getResource("/main/view/home.fxml"));

	            // Criar uma nova cena com o layout carregado
	            Scene newScene = new Scene(newRoot);

	            // Obter o estágio atual (a janela onde a tela está sendo exibida)
	            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	            currentStage.setScene(newScene); // Mudar para a nova cena
	        } catch (Exception e) {
	            e.printStackTrace();
	        
	    }
	    }
	 
	 @FXML
	    private void testeBotao() {
	    	System.out.println("Ta ino!");
	    }
	    
}
