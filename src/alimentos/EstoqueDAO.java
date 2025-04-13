package alimentos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EstoqueDAO {
	
	private String caminhoArquivo = "/greenfood/src/alimentos/data";
	
	public static void salvarEstoque(Usuario usuario, String caminhoArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            writer.write("Usuario:" + usuario.getNome());
            writer.newLine();
            for (Alimento alimento : usuario.getEstoque().getAlimentosEstoque()) {
                String linha = alimento.getNome() + ";" +
                               alimento.getCategoria() + ";" +
                               alimento.getValidade().toString() + ";" +
                               alimento.getQuantidade();
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}
