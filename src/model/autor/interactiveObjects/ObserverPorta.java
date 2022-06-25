package model.autor.interactiveObjects;

import model.autor.IVivo;
import model.nivel.IAction;
import utilidades.Posicao;

public class ObserverPorta extends Porta{

	public ObserverPorta(int x, int y, IAction iaction) {
		super(x, y, iaction);
	}

	
	@Override
	public boolean acao(Posicao destino, IVivo vivo) {
		return false;
	}


}
