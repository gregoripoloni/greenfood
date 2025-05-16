package main.controller;

import java.time.LocalDate;

import alimentos.Alimento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.MainApp;
import usuario.Doador;
import usuario.PersistenciaDoador;

public class CadastroController {
	
	 	@FXML private TextField tfNome;
	    @FXML private TextField tfQuantidade;
	    @FXML private ChoiceBox<String> cbCategoria;
	    @FXML private DatePicker dpValidade;
	    @FXML private Label errorLabel;
	    
		public void mostrarPopupSucesso() {
		    Alert alerta = new Alert(AlertType.INFORMATION);
		    alerta.setTitle("Sucesso");
		    alerta.setHeaderText(null);
		    alerta.setContentText("Ação realizada com sucesso!");
		    alerta.showAndWait();
		}
	    
	    @FXML
	    private void initialize() {
	        // Preencher as categorias no ComboBox
	        cbCategoria.getItems().addAll("Fruta", "Verdura", "Laticínio", "Carne", "Outros");
	    }
	    
	    @FXML
	    private void salvarAlimento() {
	        Doador doadorUser = (Doador) MainApp.getUser();

	        String nome = tfNome.getText();
	        String quantidadeTexto = tfQuantidade.getText();
	        String categoria = cbCategoria.getValue();
	        LocalDate validade = dpValidade.getValue();

	        // Verifica se algum campo está vazio ou nulo
	        if (nome == null || nome.isEmpty() ||
	            quantidadeTexto == null || quantidadeTexto.isEmpty() ||
	            categoria == null || validade == null) {

	            errorLabel.setText("Preencha todos os campos!");
	            return;
	        }

	        int quantidade;
	        try {
	            quantidade = Integer.parseInt(quantidadeTexto);
	        } catch (NumberFormatException e) {
	            errorLabel.setText("Quantidade Inválida!");
	            return;
	        }

	        // Tudo preenchido corretamente
	        errorLabel.setText("");

	        Alimento alimento = new Alimento(nome, quantidade, validade, categoria);
	        doadorUser.getEstoque().adicionaAoEstoque(alimento);

	        System.out.println("Alimento salvo para: " + doadorUser.getNome());
	        System.out.println("Estoque: " + doadorUser.getEstoque().getAlimentosEstoque());

	        mostrarPopupSucesso(); // Supondo que este método existe

	        PersistenciaDoador.atualizar(doadorUser);
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
	            
	            Parent newRoot = FXMLLoader.load(getClass().getResource("/main/view/MenuAlimentos.fxml"));

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


	    
	    
    
}
