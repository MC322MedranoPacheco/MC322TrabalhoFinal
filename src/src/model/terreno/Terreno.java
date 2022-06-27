package model.terreno;

import model.autor.IVivo;
import model.nivel.IAction;
import utilidades.Posicao;
import view.terrenoView.ITerrenoShow;

public abstract class Terreno implements ITerreno{

	protected IAction iAction;
	protected ITerrenoShow iTerrenoShow;
	protected Posicao posicao;
	protected int sala;
	protected boolean observavel = false;
	
	public int getSala() {
		return sala;
	}
	
	public void setSala(int sala) {
		this.sala = sala;
	}
	
	public Terreno(int x, int y, IAction iAction) {
		posicao = new Posicao(x,y);
		this.connect(iAction);
	}

	public void connect(IAction iAction) {
		this.iAction = iAction;
	}
	
	public void connect(ITerrenoShow iTerrenoShow) {
		this.iTerrenoShow = iTerrenoShow;
	}
	
	public abstract boolean acao(Posicao acao, IVivo vivo);

	public boolean getObservavel() {
		return observavel;
	}



}

