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

public class LaserMaquina extends Actor implements Observer{
	private int direcao;
	private boolean ativo;
	public LaserMaquina(int x, int y, int direcao, IAction iaction) { // fazer feixo adapter pra reduzir repeticao de codigo :) 
		super(x, y, iaction);
		
	}
	@Override
	public boolean acao(String comando, IVivo vivo) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean acao(Posicao destino, ICommand receiver, ICommand maker) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public ArrayList<Item> getInventario() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setVivo(boolean vivo) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean getVivo() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setSubejects(Subject[] sub) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
