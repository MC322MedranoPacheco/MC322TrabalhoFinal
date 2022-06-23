package model.autor.interactiveObjects;

import model.autor.Actor;
import utilidades.Posicao;

public class Parede extends Actor{

	public Parede(int x, int y) {
		super(x, y);
		int forca = 0;
		int resistencia = 10000; //resistencia maxima
	}

	@Override
	public boolean acao(String comando) {
		return false;
	}

	@Override
	public boolean acao(Posicao destino) {
		return false;
	}

	@Override
	public String toString() {
		return "P";
	}
}
