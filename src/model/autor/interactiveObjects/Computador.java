package model.autor.interactiveObjects;

import java.util.ArrayList;

import model.autor.Actor;
import model.autor.ICommand;
import model.autor.IVivo;
import model.item.Item;
import model.nivel.IAction;
import utilidades.Observer;
import utilidades.Posicao;
import utilidades.Subject;

public class Computador extends Actor implements Subject{
	String keycode = null;
	private boolean ativar;
	private boolean changed;
	private ArrayList<Observer> observers;
	private final Object MUTEX = new Object();
	
	public Computador(int x, int y, IAction iaction, String keycode) {
		super(x, y, iaction);
		this.keycode = keycode;
		changed = false;
		ativar = false;
		observers = new ArrayList<>();
		forca =2;
		resistencia = 10000;
		
	}
	
	@Override
	public boolean interact(ArrayList<Item> inventario) {
		if(keycode == null) {
			clicar();
		}
		else {
			boolean hasKey = false;
			for(Item i : inventario) {
				if(i.getItemCode().equals(keycode)) {
					hasKey = true;
					break;
				}
			}
			if(hasKey) {
				clicar();
			}
		}
		return false;
	}

	private void clicar() {
		if(ativar)
			ativar = false;
		else
			ativar = true;
		changed = true;
		notificarObservadores();
	}

	@Override
	public boolean acao(String comando, IVivo vivo) {
		return false;
	}


	@Override
	public ArrayList<Item> getInventario() {
		return null;
	}

	@Override
	public Posicao getPosicaoAnterior() {
		return null;
	}

	@Override
	public void setVivo(boolean vivo) {
		
	}

	@Override
	public boolean getVivo() {
		return false;
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
		return ativar;
	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public boolean acao(Posicao destino, ICommand receiver, ICommand maker) {
		return false;
	}

}
