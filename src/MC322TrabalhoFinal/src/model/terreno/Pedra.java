package model.terreno;

import model.autor.IVivo;
import model.nivel.IAction;
import utilidades.Posicao;

public class Pedra extends Terreno{
	public Pedra(int x, int y, IAction iaction) {
		super(x, y, iaction);
	}

	public boolean acao(Posicao destino, IVivo vivo) {
		return false;
	}

	public String toString() {
		return Pedra.class.getResource(".").getPath() + "ground_06.png";
	}
	
}
