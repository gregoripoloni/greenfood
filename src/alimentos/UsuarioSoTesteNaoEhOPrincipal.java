package alimentos;

public class UsuarioSoTesteNaoEhOPrincipal {
    private String nome;
    private String email;
    private String senha;
    private Estoque estoque = new Estoque();
    
    public UsuarioSoTesteNaoEhOPrincipal(String nome) {
    	this.nome = nome;
    }
    
	public Estoque getEstoque() {
		return estoque;
	}
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
	
	
	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
	    return "Usuario: " + nome;
	}
	
}
