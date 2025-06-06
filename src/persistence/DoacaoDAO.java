package persistence;

import model.Doador;
import utils.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Doacao;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DoacaoDAO {
    private static final String ARQUIVO = "doacoes.json";
    private final File arquivo;

    public DoacaoDAO() {
        this.arquivo = new File(ARQUIVO);
    }

    public void salvarDoacao(Doacao doacao) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(java.time.LocalDate.class, new LocalDateAdapter())
                .create();

        List<Doacao> doacoes = listarDoacoes();
        doacoes.add(doacao);

        try (Writer writer = new FileWriter(ARQUIVO)) {
            gson.toJson(doacoes, writer);
            System.out.println("Doação salva com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Doacao doacao) {
        List<Doacao> doacoes = listarDoacoes();
        for (int i = 0; i < doacoes.size(); i++) {
            if (doacoes.get(i).getId() == doacao.getId()) {
                doacoes.set(i, doacao);
                break;
            }
        }

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();

        try (Writer writer = new FileWriter(ARQUIVO)) {
            gson.toJson(doacoes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Doacao> listarDoacoes() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(java.time.LocalDate.class, new LocalDateAdapter())
                .create();

        List<Doacao> doacoes = new ArrayList<>();

        if (arquivo.exists() && arquivo.length() > 0) {
            try (Reader reader = new FileReader(ARQUIVO)) {
                Type tipoLista = new TypeToken<List<Doacao>>() {}.getType();
                doacoes = gson.fromJson(reader, tipoLista);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return doacoes;
    }
}
