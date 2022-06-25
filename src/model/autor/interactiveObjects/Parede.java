package model.autor.interactiveObjects;

import model.autor.Actor;
import model.autor.IVivo;
import model.nivel.IAction;
import utilidades.Posicao;

public class Parede extends Actor{

	public Parede(int x, int y, IAction iaction) {
		super(x, y, iaction);
		int forca = 0;
		int resistencia = 10000; //resistencia maxima
	}

	@Override
	public boolean acao(String comando, IVivo vivo) {
		return false;
	}

	@Override
	public boolean acao(Posicao destino, IVivo vivo) {
		return false;
	}


	@Override
	public void setVivo(boolean vivo) {

	}

	@Override
	public boolean getVivo() {
		return false;
	}
	
	@Override
	public String toString() {
		return Parede.class.getResource(".").getPath() + "block_04.png";
	}
}
