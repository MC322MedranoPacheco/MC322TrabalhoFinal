package model.autor;

import java.util.ArrayList;

import utilidades.Observer;
import utilidades.Posicao;
import utilidades.Subject;

public class Personagem extends Actor implements Subject{
	private ArrayList<Observer> observers;
	private boolean changed;
	private final Object MUTEX= new Object();

	public Personagem(int x, int y) {
		super(x, y);
		this.observers = new ArrayList<>();
	}
	
		//	comecando a implementar acoes de personagem
	
	
	public void acao(/*aqui precisa ter uma string */) {
		String comando = "w"; // essa string vai entrar como parametro
		
		switch(comando) {   //interpretar qual acao ele quer fazer;    
			case "w":
				iaction.mover(sala, posicaoAtual, posicaoAnterior, this.toString());
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String toString() {
		return "P";
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
	
	
	
	
	//nao serao usados para personagem !!!! :

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSubejct(Subject sub) {
		// TODO Auto-generated method stub
		
	}
	// ate aqui
	
}
