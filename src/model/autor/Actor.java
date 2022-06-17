package model.autor;

import java.util.ArrayList;

import model.nivel.IAction;
import utilidades.Observer;
import utilidades.Posicao;
import utilidades.Subject;
import view.autorView.IVisualActor;

public abstract class Actor implements IActor, Observer{
	protected IAction iaction;
	protected IVisualActor ivisualactor;
	private Subject topico;
	protected int sala;
	
	private String msg; // isso provavelmente nao vai ser uma String
	
	
	protected Posicao posicaoAtual;
	protected Posicao posicaoAnterior = null; //talvez isso era pra estar so no personagem??
	
	
	
	public Actor(int x, int y) {
		posicaoAtual = new Posicao(x, y);
	}
	
	public int getSala() {
		return sala;
	}
	public void setSala(int sala) {
		this.sala = sala;
	}
	
	
	public void connect(IAction iaction) {
		this.iaction = iaction;
	}
	
	public void connect(IVisualActor ivisualactor) {
		this.ivisualactor = ivisualactor;
	}

	@Override
	public void update() {
		 Posicao[] movimentos = (Posicao[]) topico.getUpdate(this); 
		 // implementaremos aqui as consequencias de se mover para aqui
		
	}

	@Override
	public void setSubejct(Subject sub) {
		this.topico = sub;
		
	}

	
	
	
	
	
	
	
}
