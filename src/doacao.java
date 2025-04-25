import java.io.Serializable;
import java.time.LocalDate;

public class Doacao implements Serializable {
    private String id;
    private Usuario doador;
    private Usuario receptor;
    private Alimento alimento;
    private int quantidade;
    private LocalDate data;

    public Doacao(String id, Usuario doador, Usuario receptor, Alimento alimento, int quantidade, LocalDate data) {
        this.id = id;
        this.doador = doador;
        this.receptor = receptor;
        this.alimento = alimento;
        this.quantidade = quantidade;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public Usuario getDoador() {
        return doador;
    }

    public Usuario getReceptor() {
        return receptor;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Doação [" + id + "] - Alimento: " + alimento.getNome() + ", Quantidade: " + quantidade +
                ", Doador: " + doador.getNome() + ", Receptor: " + receptor.getNome() + ", Data: " + data;
    }
}
