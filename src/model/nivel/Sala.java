package model.nivel;

import model.autor.Actor;

public class Sala {
	private Celula layout[][];
	
	public void mover(int xOri, int yOri, int xDest, int yDest, String actor) {
		Actor autor = layout[yOri][xOri].remover(actor);
		layout[yDest][xDest].setActor(autor);
	}
}