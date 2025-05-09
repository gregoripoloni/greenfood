package doacao_arquivos;
import greenfood.model.Doacao;
import greenfood.persistence.DoacaoDAO; 
import java.util.List;

public class Doacaoservice {
    private DoacaoDAO dao = new DoacaoDAO();

    public void registrarDoacao(Doacao doacao) {
        dao.salvarDoacao(doacao);
    }

    public List<Doacao> listarDoacoes() {
        return dao.obterDoacoes();
    }

    public void exibirRelatorioMensal() {
        List<Doacao> todas = listarDoacoes();
        System.out.println("📅 Relatório de Doações do Mês:");
        todas.stream()
            .filter(d -> d.getData().getMonthValue() == java.time.LocalDate.now().getMonthValue())
            .forEach(System.out::println);
    }
}
