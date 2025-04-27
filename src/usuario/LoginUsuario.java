package usuario;

import criptografia.SHA256;

import java.util.List;
import java.util.Scanner;

public class LoginUsuario {
    public static Usuario iniciar() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Login de usuário");

        System.out.println("Email: ");
        String email = scanner.nextLine();

        System.out.println("Senha: ");
        String senha = scanner.nextLine();

        Usuario usuario = verificar(email, senha);

        if (usuario == null) {
            throw new Exception("Usuário não encontrado.");
        }

        return usuario;
    }

    private static Usuario verificar(String email, String senha) {
        List<Doador> doadores = PersistenciaDoador.obter();

        for (Doador doador : doadores) {
            if (email.equals(doador.getEmail()) && SHA256.aplicar(senha).equals(doador.getSenha())) {
                return doador;
            }
        }

        List<Receptor> receptores = PersistenciaReceptor.obter();

        for (Receptor receptor : receptores) {
            if (email.equals(receptor.getEmail()) && SHA256.aplicar(senha).equals(receptor.getSenha())) {
                return receptor;
            }
        }

        return null;
    }
}
