package model.autor.interactiveObjects;

import java.util.ArrayList;

import model.autor.Actor;
import model.autor.IActor;
import utilidades.Observer;
import utilidades.Posicao;
import utilidades.Subject;

public class LaserMaquina extends Actor implements Subject{
	private int direcao;
	private ArrayList<Observer> observers;
	private boolean ativo;
	private boolean changed;
	private final Object MUTEX = new Object();
 
	public LaserMaquina(int x, int y,int direcao) {
		super(x, y);
		this.direcao = direcao;
		this.observers = new ArrayList<>();
		Posicao pos;
		switch(direcao){
		case 0:
			pos = new Posicao(x+1,y);
			break;
		case 1:
			pos = new Posicao(x-1,y);
			break;
		case 2:
			pos = new Posicao(x,y+1);
			break;
		case 3:
			pos = new Posicao(x,y-1);
			break;
		default:
			pos = null;
		}
		if(iaction.getCelula(pos, this.sala)!=null && iaction.getCelula(pos, this.sala).getActor()  == null) {
			IActor feixo = new LaserFeixo(pos.getX(), pos.getY(), direcao, this.sala);															
			feixo.connect(iaction);
			iaction.getCelula(pos,this.sala).setActor(feixo);
		}
	}
	
	

	@Override
	public boolean acao(String comando) {
		return false;
	}

	@Override
	public boolean acao(Posicao destino) {
		return false;
	}

	@Override
	public String toString() {
		return "LM";
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
		return this.ativo;
	}
	
	public void ligarDesligar() {
		if(this.ativo)
			this.ativo = false;
		else 
			this.ativo = true;
		this.changed = true;
		notificarObservadores();
	}

	
}
