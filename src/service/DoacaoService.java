package service;

import model.*;
import persistence.DoacaoDAO;

import java.time.LocalDate;
import java.util.List;

public class DoacaoService {
    private DoacaoDAO dao = new DoacaoDAO();

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

    public List<Doacao> obterPorReceptor(Receptor receptor) {
        List<Doacao> doacoes = dao.listarDoacoes();

        doacoes = doacoes.stream()
                .filter(d -> d.getIdReceptor() == receptor.getId())
                .toList();

        return doacoes;
    }

    public void adicionarReceptor(Doacao doacao, Receptor receptor) {
        doacao.setIdReceptor(receptor.getId());
        doacao.setDataRecepcao(LocalDate.now());

        dao.atualizar(doacao);
    }
}
