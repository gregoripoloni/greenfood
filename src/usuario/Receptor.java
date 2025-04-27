package usuario;

public class Receptor extends Usuario {
    public static final String TIPO = "receptor";

    public Receptor(int id, String nome, String email, String senha, String telefone) {
        super(id, nome, email, senha, telefone, TIPO);
    }
}
