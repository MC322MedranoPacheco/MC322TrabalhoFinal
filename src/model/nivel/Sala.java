package model.nivel;

import model.autor.Actor;
import model.autor.IActor;
import model.terreno.Terreno;
import utilidades.Posicao;

public class Sala {
	private Celula layout[][];
	
	public Sala(int tamanhoX, int tamanhoY) {
		layout = new Celula[tamanhoY][tamanhoX];
	}
	

	public void mover(Posicao posicaoOrigem, Posicao posicaoFinal, String actor) {
		
		
		
		IActor autor = layout[posicaoOrigem.getY()][posicaoOrigem.getY()].remover(actor);
		layout[posicaoFinal.getY()][posicaoFinal.getX()].setActor(autor);
	}


	public void adicionaTerreno(int x, int y, Terreno terreno) {
		layout[y][x] = new Celula(terreno);
	}


	public void adicionaActor(int x, int y, Actor ator) {
		layout[y][x].setActor(ator);
	}


	public int verificar(Posicao posicaoOrigem, Posicao posicaoFinal, String actor) {
		return 0;
		
	}


	public Celula getCelula(Posicao posicao) {
		return layout[posicao.getY()][posicao.getX()];
	}
}