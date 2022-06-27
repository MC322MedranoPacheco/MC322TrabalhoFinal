package model.item;

import java.util.ArrayList;

import model.autor.IActor;
import model.autor.ICommand;
import model.autor.IVivo;
import model.nivel.IAction;
import utilidades.Posicao;
import view.autorView.IVisualActor;
import view.nivelView.ObserverActor;

public  class Item{
	Posicao pos;
	String itemCode;
	
	public Item(int x, int y, String code) {
		pos = new Posicao(x, y);
		this.itemCode = code;
	}
	
	public String getItemCode() {
		return itemCode;
	}
	
	public boolean equals(Item outro) {
		boolean resultado;
		if(outro.getItemCode() == this.itemCode)
			resultado = true;
		else
			resultado = false;
		return resultado;
	}

	
}
