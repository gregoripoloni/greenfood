package main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Doacao;
import persistence.DoacaoDAO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatorioController {

    @FXML private TextArea relatorioArea;

    @FXML
    public void initialize() {
        DoacaoDAO dao = new DoacaoDAO();
        List<Doacao> doacoes = dao.listarDoacoes();

        Map<Integer, Long> doacoesPorMes = doacoes.stream()
            .collect(Collectors.groupingBy(d -> d.getData().getMonthValue(), Collectors.counting()));

        StringBuilder builder = new StringBuilder("📅 Relatório de Doações:\n\n");
        for (Map.Entry<Integer, Long> entry : doacoesPorMes.entrySet()) {
            builder.append("Mês ").append(entry.getKey())
                   .append(": ").append(entry.getValue()).append(" doações\n");
        }

        relatorioArea.setText(builder.toString());
    }

    @FXML
    private void fecharJanela() {
        Stage stage = (Stage) relatorioArea.getScene().getWindow();
        stage.close();
    }
}
