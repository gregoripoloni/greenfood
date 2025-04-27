import usuario.CadastroUsuario;
import usuario.LoginUsuario;
import usuario.Usuario;

public class Main {
    public static void main(String[] args) {
        try {
            CadastroUsuario.iniciar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Usuario usuario = LoginUsuario.iniciar();
            usuario.exibirInformacoes();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}