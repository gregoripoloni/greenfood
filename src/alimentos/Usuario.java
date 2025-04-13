package alimentos;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private Estoque estoque = new Estoque();
    
    public Usuario(String nome) {
    	this.nome = nome;
    }
    
	public Estoque getEstoque() {
		return estoque;
	}
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}
}
