package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import main.MainApp;
import model.Doador;
import model.Relatorio;
import service.RelatorioService;

import java.io.IOException;
import java.util.List;

public class RelatorioController {

    @FXML private TableView<Relatorio> tabelaDoacoes;
    @FXML private TableColumn<Relatorio, String> colMes;
    @FXML private TableColumn<Relatorio, String> colQuantidade;
    @FXML private Button botaoFechar;

    private final ObservableList<Relatorio> dadosTabela = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colMes.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getMes().toString()));
        colQuantidade.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(Integer.toString(cellData.getValue().getQuantidade())));

        carregarDoacoes();
        tabelaDoacoes.setItems(dadosTabela);
    }

    private void carregarDoacoes() {
        RelatorioService relatorioService = new RelatorioService();
        List<Relatorio> relatorios = relatorioService.gerarRelatorio(MainApp.getUser());

        dadosTabela.addAll(relatorios);
    }

    @FXML
    private void fecharJanela() throws IOException {
        Stage stage = (Stage) botaoFechar.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/main/view/home.fxml"));
        stage.setTitle("Greenfood - Home");
        stage.setScene(new Scene(root));
    }
}
