package model.autor.interactiveObjects;

import java.util.ArrayList;

import model.autor.ICommand;
import model.item.Item;
import model.nivel.IAction;
import model.terreno.Terreno;
import utilidades.Observer;
import utilidades.Posicao;
import utilidades.Subject;

public class ObserverPorta extends Porta implements Observer{
	ArrayList<Subject> subjects;

	public ObserverPorta(int x, int y, IAction iaction, int sentido) {
		super(x, y, iaction, sentido);
		subjects = new ArrayList<>();
	}
	
	

	
	@Override
	public boolean acao(Posicao destino, ICommand vivo, ICommand receiver) {
		if(!getLocked()) {
			super.acao(destino, vivo, receiver);
		}
		return false;
	}

	protected boolean getLocked() {
		return !this.chave;
	}

	@Override
	public ArrayList<Item> getInventario() {
		return null;
	}


	@Override
	public void update() {
		boolean aberta = true;
		for(Subject sub : subjects) {
			
			if(! (boolean) sub.getUpdate(this)) {
				aberta = false;

			}
		}
		this.chave = aberta;
		this.setChanged(true);
		this.notificarObservadoresView("atualizar");
	}

	@Override
	public void setSubejects(Subject[] sub) {
		for(int i =0; i< sub.length; i++) {
			if(sub[i] != null)
				subjects.add(sub[i]);
		}
	}




	public ArrayList<Subject> getSubjects() {
		return subjects;
	}




	@Override
	public Posicao getPosicaoAnterior() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public boolean interact(ArrayList<Item> inventario) {
		// TODO Auto-generated method stub
		return false;
	}
}
