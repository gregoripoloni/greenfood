package usuario;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastroUsuario {
    public static void iniciar() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Cadastro de usuário");

        System.out.println("Nome: ");
        String nome = scanner.nextLine();

        System.out.println("Email: ");
        String email = scanner.nextLine();

        System.out.println("Senha: ");
        String senha = scanner.nextLine();

        System.out.println("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.println("Tipo: ");
        String tipo = scanner.nextLine();

        String erro = validar(nome, email, senha, telefone, tipo);

        if (erro != null) {
            throw new Exception(erro);
        }

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

    private static String validar(String nome, String email, String senha, String telefone, String tipo) {
        if (nome.isEmpty()) {
            return "Nome é obrigatório.";
        }

        if (email.isEmpty()) {
            return "Email é obrigatório.";
        }
        if (!validarEmail(email)) {
            return "Email inválido.";
        }

        if (senha.isEmpty()) {
            return "Senha é obrigatória.";
        }

        if (telefone.isEmpty()) {
            return "Telefone é obrigatório.";
        }
        if (!validarTelefone(telefone)) {
            return "Telefone inválido.";
        }

        if (tipo.isEmpty()) {
            return "Tipo é obrigatório.";
        }
        if (!tipo.equals(Doador.TIPO) && !tipo.equals(Receptor.TIPO)) {
            return "Tipo inválido.";
        }

        return null;
    }

    private static boolean validarEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static boolean validarTelefone(String telefone) {
        return telefone.matches("\\d{10,11}");
    }
}
