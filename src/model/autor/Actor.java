package model.autor;

import java.util.ArrayList;

import model.nivel.IAction;
import utilidades.Observer;
import utilidades.Posicao;
import utilidades.Subject;
import view.autorView.IVisualActor;
import view.nivelView.ObserverActor;

public abstract class Actor implements IActor, Observer{
	protected IAction iaction;
	protected IVisualActor ivisualactor;
	private Subject topico;
	protected int sala;
	protected int forca;
	protected int resistencia;
	
	private String msg; // isso provavelmente nao vai ser uma String
	private final Object MUTEX= new Object();
	private boolean changedView;
	
	protected Posicao posicaoAtual;
	protected Posicao posicaoAnterior = null; //talvez isso era pra estar so no personagem??
	
	public abstract String toString();
	
	private ArrayList<ObserverActor> observersActor = new ArrayList<>();
	
	public Actor(int x, int y) {
		posicaoAtual = new Posicao(x, y);
	}
	
	public void setPosicao(Posicao posicao) {
		System.out.println(Posicao.direcaoChar(posicaoAtual, posicao));
		changedView = true;
		notificarObservadoresView(Posicao.direcaoChar(posicaoAtual, posicao));
		this.posicaoAnterior = this.posicaoAtual.clone();
		this.posicaoAtual = posicao;
		try {
			Thread.sleep(1100);
		}
		catch(Exception e) {}
	}
	
	public int getResistencia() {
		return resistencia;
	}
	
	public int getForca() {
		return forca;
	}
	
	public void setForca(int forca) {
		this.forca = forca;
	}
	
	public void setResistencia(int resistencia) {
		this.resistencia = resistencia;
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

	public void registrarView(ObserverActor obj) {
		System.out.println("wtf");
		if(obj == null) throw new NullPointerException("Null Observer");
		synchronized (MUTEX) {
		if(!observersActor.contains(obj)) {
			observersActor.add(obj);
			}
		}
	}
	
	public void excluirRegistroView(ObserverActor obj) {
		synchronized (MUTEX) {
		observersActor.remove(obj);
		}
	}
	
	public void notificarObservadoresView(String string) {

		synchronized (MUTEX) {
			if (!changedView)
				return;
			this.changedView=false;
		}
		for (ObserverActor obj : observersActor) {
			obj.update(string);
		}
	}
	
}
