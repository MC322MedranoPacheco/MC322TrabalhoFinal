package model.item;

import utilidades.Posicao;

public  class Item {
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
