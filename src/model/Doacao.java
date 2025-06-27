package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Doacao implements Serializable {
    private int id;
    private int idDoador;
    private int idReceptor;
    private LocalDate data;
    private LocalDate dataRecepcao;
    private Alimento alimento;

    public Doacao(int id, int idDoador, LocalDate data, Alimento alimento) {
        this.id = id;
        this.idDoador = idDoador;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDoador() {
        return idDoador;
    }

    public void setIdDoador(int idDoador) {
        this.idDoador = idDoador;
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

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalDate getDataRecepcao() {
        return dataRecepcao;
    }

    public void setDataRecepcao(LocalDate dataRecepcao) {
        this.dataRecepcao = dataRecepcao;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }

    @Override
    public String toString() {
        return "Doacao #" + id +
                ", idDoador: " + idDoador +
                ", idReceptor: " + idReceptor +
                ", data: " + data +
                ", dataRecepcao: " + dataRecepcao +
                ", alimento: " + alimento;
    }
}
