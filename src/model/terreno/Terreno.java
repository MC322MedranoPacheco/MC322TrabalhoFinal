package model.terreno;

import model.nivel.IAction;
import utilidades.Posicao;
import view.terrenoView.ITerrenoShow;

public abstract class Terreno implements ITerreno{

	protected IAction iAction;
	protected ITerrenoShow iTerrenoShow;
	protected Posicao posicao;
	protected int sala;
	
	public int getSala() {
		return sala;
	}
	
	public void setSala(int sala) {
		this.sala = sala;
	}
	
	public Terreno(int x, int y) {
		posicao = new Posicao(x,y);
	}

	public void connect(IAction iAction) {
		this.iAction = iAction;
	}
	
	public void connect(ITerrenoShow iTerrenoShow) {
		this.iTerrenoShow = iTerrenoShow;
	}
	
	public abstract boolean acao(Posicao acao);



}

