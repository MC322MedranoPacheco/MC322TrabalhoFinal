package model.autor.personagens;

import model.autor.Personagem;

public class Garoto extends Personagem{

	public Garoto(int x, int y) {
		super(x, y);
		this.forca = 10;
		this.resistencia = 20;
	}
	
	
	public String toString() {
		return "G";
	}
}
