package model;

import java.util.ArrayList;

public class Estoque {
	
	public Estoque() {}
	private ArrayList<Alimento> alimentosEstoque = new ArrayList<>();
	
	
	//Adiciona novo alimento ao estoque
	public void adicionaAoEstoque(Alimento alimento) {
		alimentosEstoque.add(alimento);
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder("Estoque:\n");

	    for (Alimento alimento : alimentosEstoque) {
	        sb.append(alimento).append("\n"); 
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
