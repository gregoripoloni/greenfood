package controllers;

import java.time.LocalDate;

import alimentos.Alimento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CadastroController {
	
	 	@FXML private TextField tfNome;
	    @FXML private TextField tfQuantidade;
	    @FXML private ChoiceBox<String> cbCategoria;
	    @FXML private DatePicker dpValidade;
	    
	    
	    @FXML
	    private void initialize() {
	        // Preencher as categorias no ComboBox
	        cbCategoria.getItems().addAll("Fruta", "Verdura", "Laticínio", "Carne", "Outros");
	    }
	    
	    @FXML
	    private void salvarAlimento() {
	        String nome = tfNome.getText();
	        String quantidadeTexto = tfQuantidade.getText();
	        String categoria = cbCategoria.getValue();
	        LocalDate data = dpValidade.getValue();
	        int quantidade;
	        
	        quantidade = Integer.parseInt(quantidadeTexto);
	        
	        //Alimento alimento = new Alimento(nome, quantidade, data, categoria);
	        
	        System.out.println("Salvando: " + nome + " - " + quantidade + " - " + categoria + " - " + data);
	                
	    }
	    
	    @FXML
	    private void cancelarCadastro() {
	    	tfNome.clear();
	    	tfQuantidade.clear();
	    	cbCategoria.setValue(null);
	    	dpValidade.setValue(null);
	    	
	    }
	    
	    @FXML
	    private void testeBotao() {
	    	System.out.println("Ta ino!");
	    }
	    
	    @FXML
	    private void abrirMenuAlimentos(ActionEvent event) {
	        try {
	            // Carregar a nova tela (MenuAlimentos.fxml)
	            
	            Parent newRoot = FXMLLoader.load(getClass().getResource("/application/MenuAlimentos.fxml"));

	            // Criar uma nova cena com o layout carregado
	            Scene newScene = new Scene(newRoot);

	            // Obter o estágio atual (a janela onde a tela está sendo exibida)
	            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	            currentStage.setScene(newScene); // Mudar para a nova cena
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }


	    
	    
    
}
