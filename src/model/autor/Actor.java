package model.autor;

import java.util.ArrayList;

import model.nivel.IAction;
import utilidades.Observer;
import utilidades.Posicao;
import utilidades.Subject;
import view.autorView.IVisualActor;

public abstract class Actor implements IActor, Subject{
	protected IAction iaction;
	protected IVisualActor ivisualactor;
	private int x, y;
	private ArrayList<Observer> observers;
	
	private String msg; // isso provavelmente nao vai ser uma String
	private Posicao posicaoAtual;
	private Posicao posicaoAnterior;
	
	
	private boolean changed;
	private final Object MUTEX= new Object();
	
	public Actor(int x, int y) {
		this.x = x;
		this.y =y;
		this.observers = new ArrayList<>();
	}
	

	public void connect(IAction iaction) {
		this.iaction = iaction;
	}
	
	public void connect(IVisualActor ivisualactor) {
		this.ivisualactor = ivisualactor;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	@Override
	public void registrar(Observer obj) {
		if(obj == null) throw new NullPointerException("Null Observer");
		synchronized(MUTEX) {
			if(!observers.contains(obj)) observers.add(obj);
		}
	}
	
	@Override
	public void excluirRegistro(Observer obj) {
		synchronized(MUTEX) {
			observers.remove(obj);
		}
	}
	
	@Override
	public void notificarObservadores() {
		ArrayList<Observer> observersLocal = null;
		synchronized(MUTEX) {
			if(!changed)
				return;
			observersLocal = new ArrayList<>(this.observers);
			this.changed = false;
		}
		for (Observer obj : this.observers) {
			obj.update();
		}
	}
	
	@Override
	public Object getUpdate(Observer obj) {
		Posicao[] movimentos = new Posicao[2];
		movimentos[0] = this.posicaoAtual;
		movimentos[1] = this.posicaoAnterior;
		return movimentos;
	}
	
	public void notificarMovimento(){
		System.out.println("Movimento realizado de " + posicaoAnterior + " ate " + posicaoAtual);
		this.changed=true;
		notificarObservadores();
	}

	
	
	
	
	
	
	
}
