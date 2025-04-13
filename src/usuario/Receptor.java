package usuario;

public class Receptor extends Usuario {
    public Receptor(String nome, String email, String senha, String telefone) {
        super(nome, email, senha, telefone, "receptor");
    }
}
