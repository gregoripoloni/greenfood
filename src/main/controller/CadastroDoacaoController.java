package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.MainApp;
import model.Doacao;
import model.Doador;
import persistence.DoadorDAO;
import model.Alimento;
import service.DoacaoService;

import java.io.IOException;
import java.util.List;

public class CadastroDoacaoController {

    @FXML private ComboBox<Alimento> cbAlimento;
    @FXML private TextField tfQuantidade;

    @FXML
    private Button homeMenu;
    @FXML
    private Button foodMenu;
    @FXML
    private Button donationMenu;
    @FXML
    private Button reportMenu;
    @FXML
    private Button logoutMenu;

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
    private void salvarDoacao() throws IOException {
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

        // Salva doação
        DoacaoService doacaoService = new DoacaoService();
        doacaoService.cadastrar(doadorLogado, alimentoSelecionado, quantidade);

        showAlert("Sucesso", "Doação registrada com sucesso!");
        fecharJanela();
    }

    @FXML
    private void cancelar() throws IOException {
        fecharJanela();
    }

    private void showAlert(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void fecharJanela() throws IOException {
        Stage stage = (Stage) tfQuantidade.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/main/view/home.fxml"));
        stage.setTitle("Greenfood - Home");
        stage.setScene(new Scene(root));
    }

    public void goToHome() throws IOException {
        Stage stage = (Stage) homeMenu.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/main/view/home.fxml"));
        stage.setTitle("Greenfood - Home");
        stage.setScene(new Scene(root));
    }

    public void goToFoods() throws IOException {
        Stage stage = (Stage) foodMenu.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/main/view/MenuAlimentos.fxml"));
        stage.setTitle("Greenfood - FoodList");
        stage.setScene(new Scene(root));
    }

    public void goToDonations() throws IOException {
        Stage stage = (Stage) donationMenu.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/main/view/ConsultaDoacoes.fxml"));
        stage.setTitle("Greenfood - DonationList");
        stage.setScene(new Scene(root));
    }

    public void goToReport() throws IOException {
        Stage stage = (Stage) reportMenu.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/main/view/RelatorioDoacoes.fxml"));
        stage.setTitle("Greenfood - Report");
        stage.setScene(new Scene(root));
    }

    public void logOut() throws IOException {
        MainApp.setUser(null);

        Stage stage = (Stage) logoutMenu.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/main/view/login.fxml"));
        stage.setTitle("Greenfood - LogIn");
        stage.setScene(new Scene(root));
    }
}
