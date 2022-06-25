package model.terreno;

import utilidades.Posicao;

public class Terra extends Terreno{

	public Terra(int x, int y) {
		super(x, y);
	}

	public boolean acao(Posicao destino) {
		return false;
	}
	
	public String toString() {
		return Terra.class.getResource(".").getPath() + "ground_05.png";
	}

}
