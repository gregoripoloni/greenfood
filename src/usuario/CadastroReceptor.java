package usuario;

import java.util.List;
import java.util.Scanner;

public class CadastroReceptor {
    public static void iniciar() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Cadastro de receptor\n");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        List<Receptor> receptores = PersistenciaReceptor.obter();

        int id = receptores.stream()
                .mapToInt(Usuario::getId)
                .max()
                .orElse(0) + 1;

        Receptor receptor = new Receptor(id, nome, email, senha, telefone);

        PersistenciaReceptor.salvar(receptor);
    }
}
