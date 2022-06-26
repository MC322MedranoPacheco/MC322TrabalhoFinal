package model.autor.interactiveObjects;

import model.autor.Actor;
import model.autor.ICommand;
import model.autor.IVivo;
import model.nivel.IAction;
import utilidades.Posicao;

public abstract class Porta extends Actor{
	protected int sentido;

	public Porta(int x, int y, IAction iaction, int sentido) {
		super(x, y, iaction);
		this.sentido = sentido;
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
