package service;

import model.*;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatorioService {
    public List<Relatorio> gerarRelatorio(Usuario usuario) {
        DoacaoService doacaoService = new DoacaoService();
        List<Doacao> doacoes;

        if (usuario.getTipo().equals(Doador.TIPO)) {
            doacoes = doacaoService.obterPorDoador((Doador) usuario);
        } else {
            doacoes = doacaoService.obterPorReceptor((Receptor) usuario);
        }

        Map<YearMonth, Integer> agrupadoPorMes = new HashMap<>();

        for (Doacao d : doacoes) {
            System.out.println(d.toString());

            YearMonth mes;
            if (usuario.getTipo().equals(Doador.TIPO)) {
                mes = YearMonth.from(d.getData());
            } else {
                mes = YearMonth.from(d.getDataRecepcao());
            }
            agrupadoPorMes.merge(mes, d.getAlimento().getQuantidade(), Integer::sum);
        }

        return agrupadoPorMes.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> new Relatorio(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }
}
