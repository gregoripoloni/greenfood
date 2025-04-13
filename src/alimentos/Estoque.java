package alimentos;

import java.time.LocalDate;
import java.util.ArrayList;

public class Estoque {
	private ArrayList<Alimento> alimentosEstoque = new ArrayList<>();
	
	
	//Adiciona novo alimento ao estoque
	public void adicionaAoEstoque(Alimento alimento) {
		alimentosEstoque.add(alimento);
	}
	
	
	// Adiciona quantidade para um alimento existente
	public void adicionaQuantEstoque(int idAlimento, int quant) {
		int novaQuant;
		for(Alimento alimento : alimentosEstoque) {
			if(alimento.getIdAlimento() == idAlimento) {
				novaQuant = alimento.getQuantidade() + quant;
				alimento.setQuantidade(novaQuant);
			}
		}
	}
	
	// Remove quantidade para um alimento existente
	public void removeQuantEstoque(int idAlimento, int quant) {
		int novaQuant;
		for(Alimento alimento : alimentosEstoque) {
			if(alimento.getIdAlimento() == idAlimento) {
				novaQuant = alimento.getQuantidade() - quant;
				alimento.setQuantidade(novaQuant);
			}
		}
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder("Estoque:\n");

	    for (Alimento alimento : alimentosEstoque) {
	        sb.append(alimento).append("\n"); // Isso chama o toString() de Alimento
	    }

	    return sb.toString();
	}

	
	//Getters and Setters
	
	public ArrayList<Alimento> getAlimentosEstoque() {
		return alimentosEstoque;
	}

	public void setAlimentosEstoque(ArrayList<Alimento> alimentosEstoque) {
		this.alimentosEstoque = alimentosEstoque;
	}
	
}	
