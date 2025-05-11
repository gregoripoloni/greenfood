package alimentos;

import java.time.LocalDate;

public class Alimento {
	private String nome;
	private LocalDate validade;
	private int quantidade;
	private String categoria;
	
	// Construtor
	public Alimento() {}
	
	public Alimento(String nome, int quantidade, LocalDate validade, String categoria) {
		this.nome = nome;
		this.validade = validade;
		this.quantidade = quantidade;
		this.categoria = categoria;
	}
	
	// Verifica se esta na validade
	public boolean estaValido() {
	    LocalDate limiteValidade = validade.plusDays(30);
	    return !LocalDate.now().isAfter(limiteValidade);
	}
	
	@Override
	public String toString() {
	    return "Alimento: " + nome + " | Quantidade: " + quantidade;
	}
	
	
	//Getters and Setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getValidade() {
		return validade;
	}
	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


}
