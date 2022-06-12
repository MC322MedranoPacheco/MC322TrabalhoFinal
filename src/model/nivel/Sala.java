package model.nivel;

import model.autor.Actor;
import model.terreno.Terreno;

public class Sala {
	private Celula layout[][];
	
	public Sala(int tamanhoX, int tamanhoY) {
		layout = new Celula[tamanhoY][tamanhoX];
	}
	

	public void mover(int xOri, int yOri, int xDest, int yDest, String actor) {
		Actor autor = layout[yOri][xOri].remover(actor);
		layout[yDest][xDest].setActor(autor);
	}


	public void adicionaTerreno(int x, int y, Terreno terreno) {
		layout[y][x].setTerreno(terreno);
	}


	public void adicionaActor(int x, int y, Actor ator) {
		layout[y][x].setActor(ator);
	}
}