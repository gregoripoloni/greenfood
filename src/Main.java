import usuario.CadastroUsuario;
import usuario.LoginUsuario;
import usuario.Usuario;

public class Main {
    public static void main(String[] args) {
        CadastroUsuario.iniciar();

        try {
            Usuario usuario = LoginUsuario.iniciar();
            usuario.exibirInformacoes();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}