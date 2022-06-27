package model.autor.interactiveObjects;

import java.util.ArrayList;

import model.autor.Actor;
import model.autor.ICommand;
import model.autor.IVivo;
import model.item.Item;
import model.nivel.IAction;
import utilidades.Posicao;

public class Caixa extends Actor{

	public Caixa(int x, int y, IAction iaction) {
		super(x, y, iaction);
		resistencia = 5;
		forca = 1;
	}

	@Override
	public boolean acao(String comando, IVivo vivo) {
		return false;
	}

	@Override
	public boolean acao(Posicao destino, ICommand vivo, ICommand receiver) {
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

	@Override
	public ArrayList<Item> getInventario() {
		// TODO Auto-generated method stub
		return null;
	}



}
