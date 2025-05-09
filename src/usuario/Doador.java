package usuario;

public class Doador extends Usuario {
    public static final String TIPO = "doador";

    public Doador(int id, String nome, String email, String senha, String telefone) {
        super(id, nome, email, senha, telefone, TIPO);
    }
}
