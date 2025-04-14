package alimentos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {
	
	private String caminhoArquivo = "src/alimentos/data/estoque.txt";
	
	public void salvarEstoque(UsuarioSoTesteNaoEhOPrincipal usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            writer.write("Usuario:" + usuario.getNome());
            writer.newLine();
            for (Alimento alimento : usuario.getEstoque().getAlimentosEstoque()) {
                String linha = alimento.getNome() + ";" +
                               //alimento.getCategoria() + ";" +
                               //alimento.getValidade().toString() + ";" +
                               alimento.getQuantidade();
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static List<UsuarioSoTesteNaoEhOPrincipal> carregarUsuariosComEstoques(String caminho) {
	    List<UsuarioSoTesteNaoEhOPrincipal> usuarios = new ArrayList<>();

	    try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
	        String linha;
	        UsuarioSoTesteNaoEhOPrincipal usuarioAtual = null;

	        while ((linha = reader.readLine()) != null) {
	            linha = linha.trim();

	            if (linha.startsWith("Usuario:")) {
	                String nomeUsuario = linha.substring("Usuario:".length()).trim();
	                usuarioAtual = new UsuarioSoTesteNaoEhOPrincipal(nomeUsuario); // Ajuste conforme seu construtor
	                usuarioAtual.setEstoque(new Estoque());  // Inicializa o estoque
	                usuarios.add(usuarioAtual);
	            } else if (!linha.isEmpty() && usuarioAtual != null) {
	                String[] partes = linha.split(";");
	                if (partes.length == 2) {
	                    String nomeAlimento = partes[0];
	                    int quantidade = Integer.parseInt(partes[1]);

	                    Alimento alimento = new Alimento(nomeAlimento, quantidade); // ajuste conforme seu construtor
	                    usuarioAtual.getEstoque().adicionaAoEstoque(alimento);
	                }
	            }
	        }

	        System.out.println("Usuários e estoques carregados com sucesso!");
	    } catch (IOException | NumberFormatException e) {
	        e.printStackTrace();
	        System.out.println("Erro ao carregar o arquivo.");
	    }

	    return usuarios;
	}
	
}
