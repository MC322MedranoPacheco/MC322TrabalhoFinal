package model.autor.interactiveObjects;

import model.autor.Actor;
import utilidades.Posicao;

public class Caixa extends Actor{

	public Caixa(int x, int y) {
		super(x, y);
		int resistencia = 5;
		int forca = 0;
	}

	@Override
	public boolean acao(String comando) {
		return false;
	}

	@Override
	public boolean acao(Posicao destino) {
		if(iaction.mover(sala, posicaoAtual, destino, this.toString(),this.getForca())) {
			posicaoAnterior = posicaoAtual.clone();
			posicaoAtual.setX(destino.getX());
			posicaoAtual.setY(destino.getY());
			System.out.println("moveu caixa");
			return true;
		}
		return false;
	}
	
	
	public String toString() {
		return "C";
	}

}
