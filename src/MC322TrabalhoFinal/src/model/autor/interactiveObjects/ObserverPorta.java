package model.autor.interactiveObjects;

import java.util.ArrayList;

import model.autor.ICommand;
import model.item.Item;
import model.nivel.IAction;
import utilidades.Posicao;

public class ObserverPorta extends Porta{

	public ObserverPorta(int x, int y, IAction iaction, int sentido) {
		super(x, y, iaction, sentido);
	}

	
	@Override
	public boolean acao(Posicao destino, ICommand vivo, ICommand receiver) {
		return false;
	}



	protected boolean getLocked() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public ArrayList<Item> getInventario() {
		// TODO Auto-generated method stub
		return null;
	}


}
