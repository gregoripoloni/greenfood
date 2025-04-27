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

    public static void salvar(Doador doador) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Doador> doadores = PersistenciaDoador.obter();

        doadores.add(doador);

        try (Writer writer = new FileWriter(ARQUIVO)) {
            gson.toJson(doadores, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Doador> obter() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Doador> doadores = new ArrayList<>();

        File arquivo = new File(ARQUIVO);

        if (arquivo.exists() && arquivo.length() > 0) {
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
