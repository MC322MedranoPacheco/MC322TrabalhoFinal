package model.nivel;

import java.util.ArrayList;

import model.autor.Actor;
import model.autor.ActorSubjectView;
import model.autor.IActor;
import model.item.Item;
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
	private ArrayList<Item> inventario;
	
	public Celula(){
		observers = new ArrayList<>();
		inventario = new ArrayList();
	}
	
	
	public void addItem(Item i) {
		inventario.add(i);
	}
	
	public boolean getOcupado() {
		if(autor == null || autor.getForca() == 0) {
			return true;
		}
		return false;
	}
	
	public void setActor(Actor actor) {
		this.autor = actor;
	}
	
	public void setActor(IActor actor) {
		this.autor = actor;
		if(actor.getForca() !=0 ) {
			changed = true;
			notificarObservadores();
		}
	}
	
	public IActor getActor() {
		return autor; 
	}
	
	public Terreno getTerreno() {
		return terreno;
	}
	
	public void setTerreno(Terreno terreno) {
		this.terreno = terreno;
	}
	
	public IActor remover(boolean tirar) {
		IActor autorMovendo = null;
		autorMovendo = this.autor;
		if(autor.getForca() != 0) {
			changed = true;
			notificarObservadores();
		}
		this.autor = null;
		autorMovendo.setChanged(tirar);
		autorMovendo.notificarObservadoresView("r");
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
		
		for(Observer obj : observersLocal) {
			obj.update();
			System.out.println("chegou aqui");
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

	public Item removerItem() {
		return inventario.remove(0);
	}
	

	public ArrayList<Item> getInventario() {
		return this.inventario;
	}
	
}
