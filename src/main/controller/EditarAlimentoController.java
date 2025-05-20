package main.controller;

import alimentos.Alimento;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.MainApp;
import model.Doador;
import persistence.DoadorDAO;

import java.util.List;

public class EditarAlimentoController {
	@FXML private TextField tfNome;
	@FXML private TextField tfQuantidade;	
	@FXML private DatePicker dpValidade;
	
	private Alimento alimento;
	
	  public void setAlimento(Alimento alimento) {
	        this.alimento = alimento;
	        tfNome.setText(alimento.getNome());
	        tfQuantidade.setText(String.valueOf(alimento.getQuantidade()));
	        dpValidade.setValue(alimento.getValidade());
	    }
	  
	  @FXML
	    private void salvarAlimento() {
	        alimento.setNome(tfNome.getText());
	        alimento.setQuantidade(Integer.parseInt(tfQuantidade.getText()));
	        alimento.setValidade(dpValidade.getValue());

	        // Atualizar JSON
	        Doador doadorLogado = (Doador) MainApp.getUser();
	        List<Doador> todosDoadores = DoadorDAO.recuperarTodos();

	        for (int i = 0; i < todosDoadores.size(); i++) {
	            if (todosDoadores.get(i).getEmail().equals(doadorLogado.getEmail())) {
	                todosDoadores.set(i, doadorLogado);
	                break;
	            }
	        }

	        DoadorDAO.salvarTodos(todosDoadores);

	        // Fechar janela
	        Stage stage = (Stage) tfNome.getScene().getWindow();
	        stage.close();
	    }
	  
	  @FXML
	  public void cancelarCadastro() {
	      System.out.println("Cadastro cancelado.");
	      // Aqui você pode colocar lógica para fechar a janela ou voltar para a tela anterior
	  }
	  
	
}
