package main.controller;

import model.Alimento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.MainApp;
import model.Doador;
import persistence.DoadorDAO;

import java.io.IOException;
import java.util.List;

public class MenuController {
	
	@FXML
	private VBox vboxAlimentos;
	
	@FXML
	private void initialize() {
		List<Alimento> alimentos = DoadorDAO.recuperarAlimentosDoUsuarioLogado();
		
		for (Alimento alimento : alimentos) {
		    VBox card = new VBox(10);
		    HBox botoes = new HBox(10);
		    card.setPadding(new Insets(10));
		    card.setStyle("-fx-background-color: white; -fx-border-color: #ccc; -fx-border-radius: 8; -fx-background-radius: 8;");
		    
		    Label nome = new Label("Nome: " + alimento.getNome());
		    Label qtd = new Label("Quantidade: " + alimento.getQuantidade());
		    Label validade = new Label("Validade: " + alimento.getValidade());

		    
		    Button editarBtn = new Button("Editar");
		    editarBtn.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-cursor: hand;");
		    Button excluirBtn = new Button("Excluir");
		    excluirBtn.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-cursor: hand;");
		    editarBtn.setOnAction(e -> {
		    	try {
		            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/view/editarAlimento.fxml"));
		            Parent root = loader.load();

		            EditarAlimentoController controller = loader.getController();
		            controller.setAlimento(alimento);

		            Stage stage = new Stage();
		            stage.setTitle("Editar Alimento");
		            stage.setScene(new Scene(root));
		            stage.show();

		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
		    });
		    
		    excluirBtn.setOnAction(e -> {
		    	Doador doadorLogado = (Doador) MainApp.getUser();

		        // Remove o alimento do estoque do doador logado
		        doadorLogado.getEstoque().getAlimentosEstoque().remove(alimento);

		        // Recupera todos os doadores
		        List<Doador> todosDoadores = DoadorDAO.recuperarTodos();

		        // Substitui o doador antigo pelo atualizado
		        for (int i = 0; i < todosDoadores.size(); i++) {
		            if (todosDoadores.get(i).getEmail().equals(doadorLogado.getEmail())) {
		                todosDoadores.set(i, doadorLogado);
		                break;
		            }
		        }

		        // Salva toda a lista atualizada
		        DoadorDAO.salvarTodos(todosDoadores); 

		        // Remove o card da interface
		        ((VBox) card.getParent()).getChildren().remove(card);

		        // Alerta de sucesso
		        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		        alerta.setTitle("Alimento Excluído");
		        alerta.setHeaderText(null);
		        alerta.setContentText("O alimento foi removido com sucesso!");
		        alerta.showAndWait();
		    });

		    // Adiciona os elementos ao card
		    botoes.getChildren().addAll(editarBtn, excluirBtn);

		    card.getChildren().addAll(nome, qtd, validade, botoes);

		    // Adiciona o card no VBox principal
		    vboxAlimentos.getChildren().add(card);
		}

		
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
	 
	    
}
