package model;

import alimentos.Alimento;
import alimentos.Estoque;
import java.util.ArrayList;
import java.util.List;

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

    //att lari, coloquei esse aqui em baixo para controlar la na parte de doacao :)
    
    private List<Alimento> alimentos = new ArrayList<>();

	public List<Alimento> getAlimentos() {
	    return alimentos;
	}

	public void adicionarAlimento(Alimento alimento) {
	    alimentos.add(alimento);
	}
}
