package model.terreno;


import java.util.ArrayList;

import model.autor.IVivo;
import model.nivel.Celula;
import model.nivel.IAction;
import utilidades.Observer;
import utilidades.Posicao;
import utilidades.Subject;

public class PlacaDePressao extends Terreno implements Subject, Observer{
	private boolean pressionada;
	private Subject celula;
	private ArrayList<Observer> observers;
	private boolean changed;
	private final Object MUTEX = new Object();

	public PlacaDePressao(int x, int y, Celula celula,IAction iaction) {
		super(x, y,iaction);
		Subject[] sub = new Subject[1];
		sub[0] = celula;
		setSubejects(sub);
		celula.registrar(this);
		observers = new ArrayList<>();
		observavel = true;
		
	}

	@Override
	public boolean acao(Posicao acao, IVivo vivo) {
		return true;
	}

	@Override
	public void update() {
		if((boolean )celula.getUpdate(this)) {
			pressionada = true;
			changed = true;
			notificarObservadores();
		}
		else {
			pressionada = false;
			changed = true;
			notificarObservadores();
		}
	}

	@Override
	public void setSubejects(Subject[] sub) {
		celula = sub[0];
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
			if(!changed) {
				return;
			}
			observersLocal = new ArrayList<>(this.observers);
			this.changed = false;
		}
		for(Observer obj : observersLocal) {
			obj.update();
		}
	}

	@Override
	public Object getUpdate(Observer obj) {
		return pressionada;
	}
	
	@Override
	public String toString() {
		System.out.println("criou placa de pressao");
		return PlacaDePressao.class.getResource(".").getPath() + "PlacaDePressao.png";
	}

}
