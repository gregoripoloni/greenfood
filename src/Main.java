import usuario.CadastroDoador;
import usuario.CadastroReceptor;
import usuario.LoginUsuario;
import usuario.Usuario;

public class Main {
    public static void main(String[] args) {
        CadastroDoador.iniciar();
        CadastroReceptor.iniciar();

        try {
            Usuario usuario = LoginUsuario.iniciar();
            usuario.exibirInformacoes();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}