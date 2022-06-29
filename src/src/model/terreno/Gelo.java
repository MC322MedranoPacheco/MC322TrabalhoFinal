package model.terreno;

import model.autor.IVivo;
import model.nivel.IAction;
import utilidades.Posicao;

public class Gelo extends Terreno{

	public Gelo(int x, int y, IAction iaction) {
		super(x, y, iaction);
	}

	@Override
	public boolean acao(Posicao direcao, IVivo vivo) {
		if(iAction.mover(sala, posicao, direcao, 10000)) {// forca maxima -> temos que criar uma funcao para dar essas constantes
			System.out.println("Gelo moveu ator");
			return true;
		}
		return false;
	}
	
	
	public String toString() {
		return "crate_44.png";
	}

}
