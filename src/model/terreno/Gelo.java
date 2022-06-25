package model.terreno;

import utilidades.Posicao;

public class Gelo extends Terreno{

	public Gelo(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean acao(Posicao direcao) {
		if(iAction.mover(sala, posicao, direcao, 10000)) {// forca maxima -> temos que criar uma funcao para dar essas constantes
			System.out.println("Gelo moveu ator");
			return true;
		}
		return false;
	}
	
	public String toString() {
		return Gelo.class.getResource(".").getPath() + "crate_44.png";
	}

}
