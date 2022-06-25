package model.autor.personagens;

import model.autor.Personagem;
import view.nivelView.ObserverActor;

public class Garoto extends Personagem{

	public Garoto(int x, int y) {
		super(x, y);
		this.forca = 10;
		this.resistencia = 20;
	}
	
	
	public String toString() {
		return Personagem.class.getResource(".").getPath() + "player_23.png";
	}

}
