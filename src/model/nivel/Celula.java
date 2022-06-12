package model.nivel;

import model.autor.Actor;
import model.terreno.Terreno;

public class Celula {
	private Terreno terreno;
	private Actor autor = null;
	
	public void setActor(Actor actor) {
		this.autor = actor;
	}
	
	public void setTerreno(Terreno terreno) {
		this.terreno = terreno;
	}
	
	public Actor getActor() {
		return autor; 
	}
	
	public Terreno getTerreno() {
		return terreno;
	}
	
	public Actor remover(String actor) {
		Actor autor = null;
		if(this.autor.toString().equals(actor))
			autor = this.autor; 
		return autor;
	}
}
