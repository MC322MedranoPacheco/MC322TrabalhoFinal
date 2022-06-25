package model.autor.personagens;

import model.autor.Personagem;
import model.nivel.IAction;

public class Garoto extends Personagem{

	public Garoto(int x, int y, IAction iaction) {
		super(x, y, iaction);
		this.forca = 10;
		this.resistencia = 20;
	}
	
	
	public String toString() {
		return Garoto.class.getResource(".").getPath() + "player_23.png";
	}



}
