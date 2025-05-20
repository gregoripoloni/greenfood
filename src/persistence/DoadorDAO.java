package persistence;

import alimentos.Alimento;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import main.MainApp;
import model.Doador;
import utils.LocalDateAdapter;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DoadorDAO {
    private static final String ARQUIVO = "doadores.json";

    public static void salvar(Doador doador) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();

        List<Doador> doadores = DoadorDAO.obter();
        doadores.add(doador);

        try (Writer writer = new FileWriter(ARQUIVO)) {
            gson.toJson(doadores, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Doador> obter() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();

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

    public static void atualizar(Doador doadorAtualizado) {
        List<Doador> doadores = obter();
        for (int i = 0; i < doadores.size(); i++) {
            if (doadores.get(i).getId() == doadorAtualizado.getId()) {
                doadores.set(i, doadorAtualizado);
                break;
            }
        }

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();

        try (Writer writer = new FileWriter(ARQUIVO)) {
            gson.toJson(doadores, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Listar para Receptores MenuAlimentoReceptor
    public static List<Alimento> recuperarTodosAlimentos() {
        List<Alimento> todosAlimentos = new ArrayList<>();

        List<Doador> doadores = recuperarTodos(); // já existe
        for (Doador doador : doadores) {
            todosAlimentos.addAll(doador.getEstoque().getAlimentosEstoque());
        }

        return todosAlimentos;
    }

    public static List<Alimento> recuperarAlimentosDoUsuarioLogado() {
        Doador doadorUser = (Doador) MainApp.getUser();

        if (doadorUser != null && doadorUser.getEstoque() != null) {
            return doadorUser.getEstoque().getAlimentosEstoque();
        }

        return new ArrayList<>(); // Retorna lista vazia se não houver usuário ou estoque
    }



    public static List<Doador> recuperarTodos() {
        List<Doador> doadores = new ArrayList<>();
        File pasta = new File("doadores.json"); // ou o caminho que você usa

        if (pasta.exists() && pasta.isFile()) {  // Verifica se o arquivo JSON existe
            try (FileReader reader = new FileReader(pasta)) {
                // Cria um GsonBuilder e registra o adapter personalizado
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                        .create();

                // Define o tipo para a lista de Doadores
                Type tipoLista = new TypeToken<List<Doador>>(){}.getType();
                doadores = gson.fromJson(reader, tipoLista);
            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo JSON: " + pasta.getName());
                e.printStackTrace();
            }
        }

        return doadores;
    }

    public static void salvarTodos(List<Doador> doadores) {
        try (FileWriter writer = new FileWriter("doadores.json")) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .setPrettyPrinting()
                    .create();
            gson.toJson(doadores, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar todos os doadores.");
            e.printStackTrace();
        }
    }
}
