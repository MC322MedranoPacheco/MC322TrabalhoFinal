package model.autor.personagens;

import java.util.ArrayList;

import model.autor.Personagem;
import model.item.Item;
import model.nivel.IAction;
import utilidades.Posicao;

public class Garoto extends Personagem{

	public Garoto(int x, int y, IAction iaction) {
		super(x, y, iaction);
		this.forca = 10;
		this.resistencia = 20;
	}
	
	
	public String toString() {
		return "player_23.png";
	}


	@Override
	public Posicao getPosicaoAnterior() {
		return posicaoAnterior;
	}


	





}
