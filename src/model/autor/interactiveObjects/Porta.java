package model.autor.interactiveObjects;

import model.autor.Actor;
import model.autor.IVivo;
import model.nivel.IAction;

public abstract class Porta extends Actor{

	public Porta(int x, int y, IAction iaction) {
		super(x, y, iaction);
	}
	
	@Override
	public boolean acao(String comando, IVivo vivo) {
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
		return null;
	}

}
