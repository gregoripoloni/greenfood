package usuario;

public class Receptor extends Usuario {
    public Receptor(int id, String nome, String email, String senha, String telefone) {
        super(id, nome, email, senha, telefone, "receptor");
    }
}
