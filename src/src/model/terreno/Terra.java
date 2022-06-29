package model.terreno;

import model.autor.IVivo;
import model.nivel.IAction;
import utilidades.Posicao;

public class Terra extends Terreno{

	public Terra(int x, int y, IAction iaction) {
		super(x, y,iaction);
	}

	public boolean acao(Posicao destino, IVivo vivo) {
		return false;
	}

	public String toString() {
		return Terra.class.getResource(".").getPath() + "ground_05.png";
	}
	
}
