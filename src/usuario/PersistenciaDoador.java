package usuario;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaDoador {
    private static final String ARQUIVO = "doadores.json";
    private final File arquivo;

    public PersistenciaDoador() {
        this.arquivo = new File(ARQUIVO);
    }

    public void salvaDoador(Doador doador) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Doador> doadores = this.obterDoadores();

        // 2. Adiciona o novo usuário
        doadores.add(doador);

        // 3. Escreve a lista atualizada no arquivo
        try (Writer writer = new FileWriter(ARQUIVO)) {
            gson.toJson(doadores, writer);
            System.out.println("Usuário adicionado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Doador> obterDoadores() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Doador> doadores = new ArrayList<>();

        // 1. Verifica se o arquivo já tem usuários salvos
        if (this.arquivo.exists() && this.arquivo.length() > 0) {
            try (Reader reader = new FileReader(ARQUIVO)) {
                Type tipoLista = new TypeToken<List<Doador>>() {}.getType();
                doadores = gson.fromJson(reader, tipoLista);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return doadores;
    }
}
