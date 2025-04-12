package alimentos;

import java.time.LocalDate;
import java.util.ArrayList;

public class Estoque {
	private ArrayList<Alimento> alimentosEstoque;
	
	//Construtor
	public Estoque() {
        alimentosEstoque = new ArrayList<>();
    }
	
	// Adiciona quantidade para um alimento existente
	public void adicionaEstoque(int idAlimento, int quant) {
		int novaQuant;
		for(Alimento alimento : alimentosEstoque) {
			if(alimento.getIdAlimento() == idAlimento) {
				novaQuant = alimento.getQuantidade() + quant;
				alimento.setQuantidade(novaQuant);
			}
		}
	}
	
	// Remove quantidade para um alimento existente
	public void removeEstoque(int idAlimento, int quant) {
		int novaQuant;
		for(Alimento alimento : alimentosEstoque) {
			if(alimento.getIdAlimento() == idAlimento) {
				novaQuant = alimento.getQuantidade() - quant;
				alimento.setQuantidade(novaQuant);
			}
		}
	}
	

	
	//Getters and Setters
	
	public ArrayList<Alimento> getAlimentosEstoque() {
		return alimentosEstoque;
	}

	public void setAlimentosEstoque(ArrayList<Alimento> alimentosEstoque) {
		this.alimentosEstoque = alimentosEstoque;
	}
	
}	
