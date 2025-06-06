package service;

import model.*;
import persistence.DoacaoDAO;
import persistence.DoadorDAO;
import persistence.ReceptorDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DoacaoService {
    private DoacaoDAO dao = new DoacaoDAO();

    public void registrarDoacao(Doacao doacao) {
        dao.salvarDoacao(doacao);
    }

    /* public void exibirRelatorioMensal() {
        List<Doacao> todas = listarDoacoes();
        System.out.println("📅 Relatório de Doações do Mês:");
        todas.stream()
            .filter(d -> d.getData().getMonthValue() == java.time.LocalDate.now().getMonthValue())
            .forEach(System.out::println);
    } */

    public Doacao cadastrar(Doador doador, Alimento alimento, int quantidade) {
        List<Doacao> doacoes = dao.listarDoacoes();

        int id = doacoes.stream()
                .mapToInt(Doacao::getId)
                .max()
                .orElse(0) + 1;

        Alimento alimentoDoado = new Alimento(alimento.getNome(), alimento.getQuantidade(), alimento.getValidade(), alimento.getCategoria());
        alimentoDoado.setQuantidade(quantidade);

        Doacao doacao = new Doacao(id, doador.getId(), 0, LocalDate.now(), alimentoDoado);

        dao.salvarDoacao(doacao);

        return doacao;
    }

    public List<Doacao> obterSemReceptor() {
        List<Doacao> doacoes = dao.listarDoacoes();

        doacoes = doacoes.stream()
                .filter(d -> d.getIdReceptor() == 0)
                .toList();

        return doacoes;
    }

    public List<Doacao> obterPorDoador(Doador doador) {
        List<Doacao> doacoes = dao.listarDoacoes();

        doacoes = doacoes.stream()
                .filter(d -> d.getIdDoador() == doador.getId())
                .toList();

        return doacoes;
    }

    public void adicionarReceptor(Doacao doacao, Receptor receptor) {
        doacao.setIdReceptor(receptor.getId());

        dao.atualizar(doacao);
    }
}
