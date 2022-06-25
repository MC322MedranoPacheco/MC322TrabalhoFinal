package model.nivel;

import model.autor.Actor;
import model.autor.ActorSubjectView;
import model.autor.IActor;
import model.terreno.Terreno;
import utilidades.Posicao;

public class Sala {
	private Celula layout[][];
	
	public Sala(int tamanhoX, int tamanhoY) {
		layout = new Celula[tamanhoY][tamanhoX];
	}
	

	public void mover(Posicao posicaoOrigem, Posicao posicaoFinal) {
		IActor autor = this.getCelula(posicaoOrigem).remover();
		layout[posicaoFinal.getY()][posicaoFinal.getX()].setActor(autor);
		autor.setPosicao(posicaoFinal);
	}


	public void adicionaTerreno(int x, int y, Terreno terreno) {
		layout[y][x] = new Celula(terreno);
	}


	public void adicionaActor(int x, int y, Actor ator) {
		layout[y][x].setActor(ator);
	}
	
	public void adicionaSubject(int x, int y, ActorSubjectView sub) {
		layout[y][x].setSubjectView(sub);
	}


	public int verificar(Posicao posicaoFinal) {
		if(!posicaoValida(posicaoFinal)) {
			return 1;
		}
		else if(layout[posicaoFinal.getY()][posicaoFinal.getX()].getActor() != null) {
			return 0;
		}
		return 2; // cai no default
	}
	
	public boolean posicaoValida(Posicao posicao) {
		if((layout.length <= posicao.getY() || posicao.getY() < 0 || posicao.getX() < 0))
			return false;
		else if(layout[posicao.getY()].length <= posicao.getX())
			return false;
		else {
			return true;
		}
	}


	public Celula getCelula(Posicao posicao) {
		return layout[posicao.getY()][posicao.getX()];
	}
}