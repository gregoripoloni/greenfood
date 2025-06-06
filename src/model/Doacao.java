package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Doacao implements Serializable {
    private int id;
    private int idDoador;
    private int idReceptor;
    private LocalDate data;
    private Alimento alimento;

    public Doacao(int id, int idDoador, int idReceptor, LocalDate data, Alimento alimento) {
        this.id = id;
        this.idDoador = idDoador;
        this.idReceptor = idReceptor;
        this.data = data;
        this.alimento = alimento;
    }

    public boolean verificarValidade() {
        if (alimento.getValidade().isBefore(LocalDate.now())) {
            return false;
        }
        return true;
    }

    public void atualizarQuantidade(String nomeAlimento, int novaQuantidade) {
        if (alimento.getNome().equalsIgnoreCase(nomeAlimento)) {
            alimento.setQuantidade(novaQuantidade);
        }
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public int getIdDoador() {
        return idDoador;
    }

    public int getIdReceptor() {
        return idReceptor;
    }

    public void setIdReceptor(int idReceptor) {
        this.idReceptor = idReceptor;
    }

    public LocalDate getData() {
        return data;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    @Override
    public String toString() {
        return "Doacao #" + id +
                ", idDoador: " + idDoador +
                ", idReceptor: " + idReceptor +
                ", data: " + data +
                ", alimento: " + alimento;
    }
}
