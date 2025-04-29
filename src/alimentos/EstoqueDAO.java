package alimentos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class EstoqueDAO {
	
	private Gson criarGson() {
        return new GsonBuilder()
                .registerTypeAdapter(java.time.LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
    }
	
	
	public void salvarEstoque(List<UsuarioSoTesteNaoEhOPrincipal> usuarios) {
		Gson gson = criarGson();
		
		String caminho = "src/alimentos/data/estoque.json";
		
		try (FileWriter writer = new FileWriter(caminho )) {
			gson.toJson(usuarios, writer);
		}
		catch(IOException e) {
			    System.out.println("Erro ao Salvar: " + e.getMessage());
			    e.printStackTrace();
			}

		
	}


	public List<UsuarioSoTesteNaoEhOPrincipal> recuperarEstoqueUsers(String caminhoArquivo) {
		List<UsuarioSoTesteNaoEhOPrincipal> usuarios = new ArrayList<>();
		Gson gson = criarGson();
		
		try (FileReader reader = new FileReader(caminhoArquivo)) {
			Type listType = new TypeToken<List<UsuarioSoTesteNaoEhOPrincipal>>() {}.getType();
			usuarios = gson.fromJson(reader, listType);
		} catch (IOException e) {
			System.out.println("Erro ao recuperar lista: " + e.getMessage());
			return null;
		}
		return usuarios;
	}
}
	
	

