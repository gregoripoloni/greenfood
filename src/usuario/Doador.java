package usuario;

public class Doador extends Usuario {
    public Doador(String nome, String email, String senha, String telefone) {
        super(nome, email, senha, telefone, "doador");
    }
}
