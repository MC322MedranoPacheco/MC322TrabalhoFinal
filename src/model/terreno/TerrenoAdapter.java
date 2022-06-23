package model.terreno;

import model.autor.ICommand;
import utilidades.Posicao;

public class TerrenoAdapter implements ICommand {

	private Terreno adaptee;
	
	public TerrenoAdapter(Terreno adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public boolean acao(String comando) {
		return false;
	}

	@Override
	public boolean acao(Posicao destino) {
		return adaptee.acao(destino);
	}

	@Override
	public int getForca() {
		return 0;
	}

	@Override
	public int getResistencia() {
		return 0;
	}


	

}
