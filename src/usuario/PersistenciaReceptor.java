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

    public static void salvar(Receptor receptor) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Receptor> receptores = PersistenciaReceptor.obter();

        receptores.add(receptor);

        try (Writer writer = new FileWriter(ARQUIVO)) {
            gson.toJson(receptores, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Receptor> obter() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Receptor> receptores = new ArrayList<>();

        File arquivo = new File(ARQUIVO);

        if (arquivo.exists() && arquivo.length() > 0) {
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
