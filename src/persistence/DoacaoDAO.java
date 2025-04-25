package persistence;


import java.io.*;
import java.util.*;

import doacao_arquivos.Doacao;

public class DoacaoDAO {
    private static final String FILE = "doacoes.json";
    private final Gson gson = new Gson();

    public void salvar(Doacao doacao) {
        List<Doacao> doacoes = listar();
        doacoes.add(doacao);
        try (Writer writer = new FileWriter(FILE)) {
            gson.toJson(doacoes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Doacao> listar() {
        try (Reader reader = new FileReader(FILE)) {
            Type listType = new TypeToken<ArrayList<Doacao>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}