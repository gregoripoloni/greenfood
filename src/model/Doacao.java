package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import alimentos.Alimento;
import usuario.Usuario;

public class Doacao implements Serializable {
    private int idDoacao;
    private Usuario doador;
    private Usuario receptor;
    private LocalDate data;
    private List<Alimento> alimentos;

    public Doacao(int idDoacao, Usuario doador, Usuario receptor, LocalDate data, List<Alimento> alimentos) {
        this.idDoacao = idDoacao;
        this.doador = doador;
        this.receptor = receptor;
        this.data = data;
        this.alimentos = alimentos;
    }

    public boolean verificarValidade() {
        for (Alimento alimento : alimentos) {
            if (alimento.getValidade().isBefore(LocalDate.now())) {
                return false;
            }
        }
        return true;
    }

    public void atualizarQuantidade(String nomeAlimento, int novaQuantidade) {
        for (Alimento alimento : alimentos) {
            if (alimento.getNome().equalsIgnoreCase(nomeAlimento)) {
                alimento.setQuantidade(novaQuantidade);
                return;
            }
        }
    }

    // Getters e Setters

    public int getIdDoacao() {
        return idDoacao;
    }

    public Usuario getDoador() {
        return doador;
    }

    public Usuario getReceptor() {
        return receptor;
    }

    public LocalDate getData() {
        return data;
    }

    public List<Alimento> getAlimentos() {
        return alimentos;
    }

    @Override
    public String toString() {
        return "Doacao #" + idDoacao +
                ", doador: " + doador.getNome() +
                ", receptor: " + receptor.getNome() +
                ", data: " + data +
                ", alimentos: " + alimentos;
    }
}
