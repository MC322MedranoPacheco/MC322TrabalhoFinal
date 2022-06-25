package model.terreno;

import model.autor.ICommand;
import model.autor.IVivo;
import utilidades.Posicao;

public class TerrenoAdapter implements ICommand {

	private Terreno adaptee;
	
	public TerrenoAdapter(Terreno adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public boolean acao(String comando,IVivo vivo) {
		return false;
	}

	@Override
	public boolean acao(Posicao destino, IVivo vivo) {
		return adaptee.acao(destino, vivo);
	}

	@Override
	public int getForca() {
		return 0;
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


	

}
