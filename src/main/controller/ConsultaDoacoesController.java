package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Alimento;
import model.Doacao;
import persistence.DoacaoDAO;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ConsultaDoacoesController {

    @FXML private TableView<Alimento> tabelaDoacoes;
    @FXML private TableColumn<Alimento, String> colNome;
    @FXML private TableColumn<Alimento, String> colCategoria;
    @FXML private TableColumn<Alimento, String> colValidade;

    private final ObservableList<Alimento> dadosTabela = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNome()));
        colCategoria.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCategoria()));
        colValidade.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getValidade().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
        );

        carregarDoacoes();
        tabelaDoacoes.setItems(dadosTabela);
    }

    private void carregarDoacoes() {
        DoacaoDAO dao = new DoacaoDAO();
        List<Doacao> doacoes = dao.listarDoacoes();
        for (Doacao d : doacoes) {
            dadosTabela.addAll(d.getAlimentos());
        }
    }

    @FXML
    private void fecharJanela() {
        Stage stage = (Stage) tabelaDoacoes.getScene().getWindow();
        stage.close();
    }
}
