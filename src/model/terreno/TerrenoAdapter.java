package model.terreno;

import java.util.ArrayList;

import model.autor.ICommand;
import model.autor.IVivo;
import model.item.Item;
import utilidades.Posicao;

public class TerrenoAdapter implements ICommand {

	private Terreno adaptee;
	
	public TerrenoAdapter(Terreno adaptee) {
		this.adaptee = adaptee;
	}
	


	@Override
	public boolean acao(Posicao destino, ICommand vivo, ICommand receiver) {
		return adaptee.acao(destino, vivo);
	}

	@Override
	public int getForca() {
		return 10000;
	}

	@Override
	public int getResistencia() {
		return 0;
	}

	@Override
	public void setVivo(boolean vivo) {
	}

	@Override
	public boolean getVivo() {
		return false;
	}

	@Override
	public ArrayList<Item> getInventario() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean acao(String comando, IVivo vivo) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public Posicao getPosicao() {
		// TODO Auto-generated method stub
		return null;
	}


	

}
