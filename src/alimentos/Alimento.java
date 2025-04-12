package alimentos;

import java.time.LocalDate;

public class Alimento {
	private int idAlimento;
	private String nome;
	private LocalDate validade;
	private int quantidade;
	private Categoria categoria;
	
	// Construtor
	public Alimento(String nome, LocalDate validade, int quantidade, Categoria categoria) {
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
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public int getIdAlimento() {
		return idAlimento;
	}


}
