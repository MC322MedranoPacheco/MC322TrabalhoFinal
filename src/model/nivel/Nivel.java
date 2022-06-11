package model.nivel;

public class Nivel implements INivel{
	private Sala salas[];

	public void mover(int sala, int xOri, int yOri, int xDest, int yDest, String actor) {
		salas[sala].mover(xOri, yOri, xDest, yDest, actor);
	}
	
}
