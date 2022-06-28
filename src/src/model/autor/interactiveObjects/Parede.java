package model.autor.interactiveObjects;

import java.util.ArrayList;

import model.autor.Actor;
import model.autor.ICommand;
import model.autor.IVivo;
import model.item.Item;
import model.nivel.IAction;
import utilidades.Posicao;

public class Parede extends Actor{

	public Parede(int x, int y, IAction iaction) {
		super(x, y, iaction);
		int forca = 1;
		int resistencia = 10000; //resistencia maxima
	}

	@Override
	public boolean acao(String comando, IVivo vivo) {
		return false;
	}

	@Override
	public boolean acao(Posicao destino, ICommand vivo, ICommand receiver) {
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

	@Override
	public ArrayList<Item> getInventario() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Posicao getPosicaoAnterior() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean interact(ArrayList<Item> inventario) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public int getForca() {
		return 2;
	}
}
