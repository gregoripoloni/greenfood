package usuario;

import alimentos.Estoque;

public class Doador extends Usuario {
    public static final String TIPO = "doador";
    //private Estoque estoque = new Estoque();
    
    public Doador(int id, String nome, String email, String senha, String telefone) {
        super(id, nome, email, senha, telefone, TIPO);
        //this.estoque = new Estoque(); 
    }

	/*public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}*/
    
    
}
