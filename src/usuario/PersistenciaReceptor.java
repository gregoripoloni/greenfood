package usuario;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaReceptor {
    private static final String ARQUIVO = "receptores.json";
    private final File arquivo;

    public PersistenciaReceptor() {
        this.arquivo = new File(ARQUIVO);
    }

    public void salvaReceptor(Receptor receptor) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Receptor> receptores = this.obterReceptores();

        // 2. Adiciona o novo usuário
        receptores.add(receptor);

        // 3. Escreve a lista atualizada no arquivo
        try (Writer writer = new FileWriter(ARQUIVO)) {
            gson.toJson(receptores, writer);
            System.out.println("Usuário adicionado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Receptor> obterReceptores() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Receptor> receptores = new ArrayList<>();

        // 1. Verifica se o arquivo já tem usuários salvos
        if (this.arquivo.exists() && this.arquivo.length() > 0) {
            try (Reader reader = new FileReader(ARQUIVO)) {
                Type tipoLista = new TypeToken<List<Receptor>>() {}.getType();
                receptores = gson.fromJson(reader, tipoLista);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return receptores;
    }
}
