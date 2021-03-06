package model.autor;

import java.util.ArrayList;

import model.item.Item;
import model.nivel.IAction;
import utilidades.Observer;
import utilidades.Posicao;
import utilidades.Subject;

public abstract class Personagem extends Actor implements Subject{
	private ArrayList<Observer> observers;
	private boolean changed;
	private final Object MUTEX= new Object();
	private boolean vivo = true;
	protected ArrayList<Item> inventario = new ArrayList<Item>();

	public Personagem(int x, int y, IAction iaction) {
		super(x, y, iaction);
		this.observers = new ArrayList<>();
		this.inventario = new ArrayList<>();
	}
	
		//	comecando a implementar acoes de personagem
	
	
	public boolean acao(String comando, IVivo vivo){ 
		
		if(comando == "w" || comando == "a" || comando == "s" || comando== "d" || comando== "p" || comando == "k") {
			Posicao destino;
			switch(comando) {   //interpretar qual acao ele quer fazer;    
				case "w":
					destino = new Posicao(posicaoAtual.getX(), posicaoAtual.getY()-1);
					if(iaction.mover(sala, posicaoAtual, destino,this.getForca())) {
						System.out.println("moveu pra cima");
						return true;
					}
					return false;
					
				case "s":
					destino = new Posicao(posicaoAtual.getX(), posicaoAtual.getY()+1);
					if(iaction.mover(sala, posicaoAtual, destino,this.getForca())) {
						System.out.println("moveu pra baixo");
						return true;
					}
					return false;
					
				case "a":
					destino = new Posicao(posicaoAtual.getX()-1, posicaoAtual.getY());
					if(iaction.mover(sala, posicaoAtual, destino,this.getForca())) {
						System.out.println("moveu pro lado");
						return true;
					}
					return false;
					
				case "d":
					destino = new Posicao(posicaoAtual.getX()+1, posicaoAtual.getY());
					if(iaction.mover(sala, posicaoAtual, destino,this.getForca())) {
						System.out.println("moveu pro lado");
						return true;
					}
					return false;
				case "p":
					iaction.pegar(sala, posicaoAtual, inventario);
					iaction.removerItem(sala, posicaoAtual);
					return true;
				case "i":
					interact(inventario);
					return true;
			}
		}
		return false;
		
	}
	
	@Override
	public boolean interact(ArrayList<Item> inventario) {
		iaction.interact(sala,posicaoAtual, inventario);
		return false;
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
	

	@Override
	public boolean acao(Posicao destino, ICommand vivo, ICommand receiver) { // no teleporte vai usar
		return false;
	}
	
	public boolean getVivo() {
		return vivo;
	}
	
	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}
	
	@Override
	public ArrayList<Item> getInventario() {
		return this.inventario;
	}
	
}
