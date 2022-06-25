package model.nivel;

import java.util.ArrayList;

import model.autor.Actor;
import model.autor.ActorSubjectView;
import model.autor.IActor;
import model.terreno.ITerreno;
import model.terreno.Terreno;
import utilidades.Observer;
import utilidades.Subject;

public class Celula implements Subject{
	private Terreno terreno;
	private IActor autor = null; 
	private ArrayList<Observer> observers;
	private boolean changed;
	private final Object MUTEX = new Object();
	private ActorSubjectView subjectView = null;
	
	public Celula(Terreno terreno){
		this.terreno = terreno;
		observers = new ArrayList<>();
	}
	
	public boolean getOcupado() {
		if(autor == null || autor.toString().charAt(0) == 'L') {
			return false;
		}
		return true;
	}
	
	public void setActor(Actor actor) {
		this.autor = actor;
	}
	
	public void setActor(IActor actor) {
		this.autor = actor;
		notificarObservadores();
	}
	
	public IActor getActor() {
		return autor; 
	}
	
	public Terreno getTerreno() {
		return terreno;
	}
	
	public IActor remover() {
		IActor autorMovendo = null;
		autorMovendo = this.autor;
		this.autor = null;
		notificarObservadores();
		return autorMovendo;
	}

	@Override
	public void registrar(Observer obj) {
		if(obj == null) throw new NullPointerException("Null Observer");
		synchronized(MUTEX) {
			if(!observers.contains(obj))observers.add(obj);
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
		for(Observer obj : this.observers) {
			obj.update();
		}
	}

	@Override
	public Object getUpdate(Observer obj) {
		return getOcupado();
	}
	
	
	public void setSubjectView(ActorSubjectView subjectView) {
		this.subjectView = subjectView;
	}

	public ActorSubjectView subjectView() {
		return autor; 
	}
	
	
}
