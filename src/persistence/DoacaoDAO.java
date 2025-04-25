package persistence;


import java.io.*;
import java.util.*;

import doacao_arquivos.Doacao;

public class DoacaoDAO {
    private static final String FILE = "doacoes.dat";

    public void salvar(Doacao doacao) {
        List<Doacao> doacoes = listar();
        doacoes.add(doacao);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(doacoes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Doacao> listar() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
            return (List<Doacao>) in.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}