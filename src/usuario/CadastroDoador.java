package usuario;

import java.util.List;
import java.util.Scanner;

public class CadastroDoador {
    public static void iniciar() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Cadastro de doador\n");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        List<Doador> doadores = PersistenciaDoador.obter();

        int id = doadores.stream()
                .mapToInt(Usuario::getId)
                .max()
                .orElse(0) + 1;

        Doador doador = new Doador(id, nome, email, senha, telefone);

        PersistenciaDoador.salvar(doador);
    }
}
