import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import Doacao;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DoacaoDAO {
    private static final String ARQUIVO = "doacoes.json";
    private final File arquivo;

    public DoacaoDAO() {
        this.arquivo = new File(ARQUIVO);
    }

    public void salvarDoacao(Doacao doacao) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Doacao> doacoes = this.obterDoacoes();

        doacoes.add(doacao);

        try (Writer writer = new FileWriter(ARQUIVO)) {
            gson.toJson(doacoes, writer);
            System.out.println("✅ Doação registrada com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Doacao> obterDoacoes() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Doacao> doacoes = new ArrayList<>();

        if (this.arquivo.exists() && this.arquivo.length() > 0) {
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