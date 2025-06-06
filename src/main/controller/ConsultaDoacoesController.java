package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.MainApp;
import model.Doacao;
import model.Doador;
import model.Receptor;
import service.DoacaoService;
import service.UsuarioService;

import java.io.IOException;
import java.util.List;

public class ConsultaDoacoesController {

    @FXML private VBox listaDoacoes;
    @FXML private Button botaoFechar;

    @FXML
    public void initialize() {
        carregarDoacoes();
    }

    private void carregarDoacoes() {
        List<Doacao> doacoes;
        DoacaoService doacaoService = new DoacaoService();
        if (MainApp.getUser().getTipo().equals(Doador.TIPO)) {
            doacoes = doacaoService.obterPorDoador((Doador) MainApp.getUser());
        } else {
            doacoes = doacaoService.obterSemReceptor();
        }
        montarLista(doacoes);
    }

    private void montarLista(List<Doacao> doacoes) {
        for (Doacao doacao : doacoes) {
            VBox card = new VBox(10);
            HBox botoes = new HBox(10);
            card.setPadding(new Insets(10));
            card.setStyle("-fx-background-color: white; -fx-border-color: #ccc; -fx-border-radius: 8; -fx-background-radius: 8;");

            UsuarioService usuarioService = new UsuarioService();
            String nomeDoador = usuarioService.obterDoadorPorId(doacao.getIdDoador()).getNome();

            Label nome = new Label("Doador: " + nomeDoador);
            Label alimento = new Label("Alimento: " + doacao.getAlimento().getNome());
            Label validade = new Label("Validade: " + doacao.getAlimento().getValidade());

            if (MainApp.getUser().getTipo().equals(Receptor.TIPO)) {
                Button editarBtn = new Button("Receber");
                editarBtn.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-cursor: hand;");
                editarBtn.setOnAction(e -> {
                    DoacaoService doacaoService = new DoacaoService();
                    doacaoService.adicionarReceptor(doacao, (Receptor) MainApp.getUser());
                    editarBtn.setVisible(false);
                });
                botoes.getChildren().addAll(editarBtn);
            }

            card.getChildren().addAll(nome, alimento, validade, botoes);
            listaDoacoes.getChildren().add(card);
        }
    }

    @FXML
    private void fecharJanela() throws IOException {
        Stage stage = (Stage) botaoFechar.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/main/view/home.fxml"));
        stage.setTitle("Greenfood - Home");
        stage.setScene(new Scene(root));
    }
}
