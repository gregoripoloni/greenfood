package service;

import model.Doador;
import model.Receptor;
import model.Usuario;
import persistence.DoadorDAO;
import persistence.ReceptorDAO;
import utils.SHA256;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsuarioService {
    public Usuario obterDoadorPorId(int idDoador) {
        List<Doador> doadores = DoadorDAO.obter();

        Doador doador = doadores.stream()
                .filter(d -> d.getId() == idDoador)
                .findFirst().get();

        return doador;
    }

    public Usuario cadastrar(String nome, String email, String senha, String telefone, String tipo) throws Exception {
        String erro = validar(nome, email, senha, telefone, tipo);

        if (erro != null) {
            throw new Exception(erro);
        }

        if (tipo.equals(Doador.TIPO)) {
            List<Doador> doadores = DoadorDAO.obter();

            int id = doadores.stream()
                    .mapToInt(Usuario::getId)
                    .max()
                    .orElse(0) + 1;

            Doador doador = new Doador(id, nome, email, senha, telefone);

            DoadorDAO.salvar(doador);

            return doador;
        }

        if (tipo.equals(Receptor.TIPO)) {
            List<Receptor> receptores = ReceptorDAO.obter();

            int id = receptores.stream()
                    .mapToInt(Usuario::getId)
                    .max()
                    .orElse(0) + 1;

            Receptor receptor = new Receptor(id, nome, email, senha, telefone);

            ReceptorDAO.salvar(receptor);

            return receptor;
        }

        return null;
    }

    public Usuario autenticar(String email, String senha) throws Exception {
        List<Doador> doadores = DoadorDAO.obter();

        for (Doador doador : doadores) {
            if (email.equals(doador.getEmail()) && SHA256.aplicar(senha).equals(doador.getSenha())) {
                return doador;
            }
        }

        List<Receptor> receptores = ReceptorDAO.obter();

        for (Receptor receptor : receptores) {
            if (email.equals(receptor.getEmail()) && SHA256.aplicar(senha).equals(receptor.getSenha())) {
                return receptor;
            }
        }

        throw new Exception("E-mail ou senha incorretos.");
    }

    private String validar(String nome, String email, String senha, String telefone, String tipo) {
        if (nome.isEmpty()) {
            return "Nome é obrigatório.";
        }

        if (telefone.isEmpty()) {
            return "Telefone é obrigatório.";
        }
        if (!validarTelefone(telefone)) {
            return "Telefone inválido.";
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

        if (tipo == null || tipo.isEmpty()) {
            return "Tipo é obrigatório.";
        }
        if (!tipo.equals(Doador.TIPO) && !tipo.equals(Receptor.TIPO)) {
            return "Tipo inválido.";
        }

        return null;
    }

    private boolean validarEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validarTelefone(String telefone) {
        return telefone.matches("\\d{10,11}");
    }
}
