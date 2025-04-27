package usuario;

import java.util.List;
import java.util.Scanner;

public class CadastroUsuario {
    public static void iniciar() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Cadastro de usuário\n");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();

        if (tipo.equals(Doador.TIPO)) {
            List<Doador> doadores = PersistenciaDoador.obter();

            int id = doadores.stream()
                    .mapToInt(Usuario::getId)
                    .max()
                    .orElse(0) + 1;

            Doador doador = new Doador(id, nome, email, senha, telefone);

            PersistenciaDoador.salvar(doador);
        }

        if (tipo.equals(Receptor.TIPO)) {
            List<Receptor> receptores = PersistenciaReceptor.obter();

            int id = receptores.stream()
                    .mapToInt(Usuario::getId)
                    .max()
                    .orElse(0) + 1;

            Receptor receptor = new Receptor(id, nome, email, senha, telefone);

            PersistenciaReceptor.salvar(receptor);
        }
    }
}
