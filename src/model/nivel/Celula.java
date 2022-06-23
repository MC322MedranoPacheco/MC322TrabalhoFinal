package model.nivel;

import model.autor.Actor;
import model.autor.IActor;
import model.terreno.ITerreno;
import model.terreno.Terreno;

public class Celula {
	private Terreno terreno;
	private IActor autor = null; // aqui tem que ser um Iactor
	
	public Celula(Terreno terreno){
		this.terreno = terreno;
	}
	
	public void setActor(Actor actor) {
		this.autor = actor;
	}
	
	public void setActor(IActor actor) {
		this.autor = actor;
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
		return autorMovendo;
	}
}
