package model;

import alimentos.Estoque;

public class Doador extends Usuario {
    public static final String TIPO = "doador";
    private Estoque estoque;
    
    public Doador(int id, String nome, String email, String senha, String telefone) {
        super(id, nome, email, senha, telefone, TIPO);
        this.estoque = new Estoque(); 
    }

    public Estoque getEstoque() {
        if (estoque == null) {
            estoque = new Estoque();
        }
        return estoque;
    }

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
    
    
}
