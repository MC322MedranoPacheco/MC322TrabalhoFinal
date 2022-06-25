package model.autor.interactiveObjects;

import model.autor.Actor;
import model.autor.IVivo;
import model.nivel.IAction;
import utilidades.Posicao;

public class Caixa extends Actor{

	public Caixa(int x, int y, IAction iaction) {
		super(x, y, iaction);
		int resistencia = 5;
		int forca = 1;
	}

	@Override
	public boolean acao(String comando, IVivo vivo) {
		return false;
	}

	@Override
	public boolean acao(Posicao destino, IVivo vivo) {
		if(iaction.mover(sala, posicaoAtual, destino,this.getForca())) {
			System.out.println("moveu caixa");
			return true;
		}
		return false;
	}
	
	
	public String toString() {
		return Caixa.class.getResource(".").getPath() + "crate_42.png";
	}


	@Override
	public void setVivo(boolean vivo) {

	}

	@Override
	public boolean getVivo() {
		return false;
	}

}
