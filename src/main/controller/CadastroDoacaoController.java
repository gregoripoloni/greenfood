package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.MainApp;
import model.Doador;
import persistence.DoadorDAO;
import model.Alimento;

import java.util.List;

public class CadastroDoacaoController {

    @FXML private ComboBox<Alimento> cbAlimento;
    @FXML private TextField tfQuantidade;

    private Doador doadorLogado;

    @FXML
    public void initialize() {
        if (MainApp.getUser() instanceof Doador) {
            doadorLogado = (Doador) MainApp.getUser();

            List<Alimento> alimentos = doadorLogado.getEstoque().getAlimentosEstoque(); // se estiver usando estoque

            if (alimentos != null && !alimentos.isEmpty()) {
                cbAlimento.getItems().addAll(alimentos);
            } else {
                System.out.println("Nenhum alimento encontrado no estoque do doador.");
            }
        } else {
            System.out.println("Usuário não é um doador ou não está logado.");
        }
    }

    @FXML
    private void salvarDoacao() {
        Alimento alimentoSelecionado = cbAlimento.getValue();
        String qtdTexto = tfQuantidade.getText();

        if (alimentoSelecionado == null || qtdTexto.isEmpty()) {
            showAlert("Erro", "Selecione um alimento e insira a quantidade.");
            return;
        }

        int quantidade;
        try {
            quantidade = Integer.parseInt(qtdTexto);
        } catch (NumberFormatException e) {
            showAlert("Erro", "Quantidade inválida.");
            return;
        }

        if (quantidade <= 0 || quantidade > alimentoSelecionado.getQuantidade()) {
            showAlert("Erro", "Quantidade fora do limite disponível.");
            return;
        }

        // Atualiza a quantidade no alimento
        alimentoSelecionado.setQuantidade(alimentoSelecionado.getQuantidade() - quantidade);

        // Atualiza JSON do doador
        List<Doador> todos = DoadorDAO.recuperarTodos();
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).getEmail().equals(doadorLogado.getEmail())) {
                todos.set(i, doadorLogado);
                break;
            }
        }
        DoadorDAO.salvarTodos(todos);

        showAlert("Sucesso", "Doação registrada com sucesso!");
        fecharJanela();
    }

    @FXML
    private void cancelar() {
        fecharJanela();
    }

    private void showAlert(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void fecharJanela() {
        Stage stage = (Stage) tfQuantidade.getScene().getWindow();
        stage.close();
    }
}
