package model.nivel;

import model.autor.Actor;
import model.autor.ActorSubjectView;
import model.autor.IActor;
import model.autor.interactiveObjects.LaserFeixo;
import model.autor.personagens.Garoto;
import model.terreno.Terreno;
import view.nivelView.INivelView;
import utilidades.Posicao;
import view.nivelView.NivelView;

public class Sala {
	private Celula layout[][];
	private INivelView nivelView;
	
	public Sala(int tamanhoX, int tamanhoY) {
		layout = new Celula[tamanhoY][tamanhoX];
		for(int i =0; i < tamanhoY; i++) {
			for(int j = 0; j < tamanhoX; j++) {
				layout[i][j] =  new Celula();
			}
		}
	}
	
	public  void setNivelView(INivelView nivelView) {
		this.nivelView = nivelView;
	}
	
	public INivelView getNivelView() {
		return nivelView;
	}
	
	public int getTamanho() {
		return layout.length;
	}
	
	public void mover(Posicao posicaoOrigem, Posicao posicaoFinal) {
		IActor autor = this.getCelula(posicaoOrigem).remover(false);
		layout[posicaoFinal.getY()][posicaoFinal.getX()].setActor(autor);
		autor.setPosicao(posicaoFinal);
	}


	public void adicionaTerreno(int x, int y, Terreno terreno) {
		layout[y][x].setTerreno(terreno);
	}


	public void adicionaActor(int x, int y, Actor ator) {
		layout[y][x].setActor(ator);
	}


	public int verificar(Posicao posicaoFinal) {
		if(!posicaoValida(posicaoFinal)) {
			return 1;
		}
		else if(layout[posicaoFinal.getY()][posicaoFinal.getX()].getActor() != null ) {
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
	
	
	public void adicionaSubject(int x, int y, ActorSubjectView sub) {
		layout[y][x].setSubjectView(sub);
	}
	
	public Posicao getPosPersonagem() {
		for(int i = 0; i < layout.length; i++) 
			for(int k = 0; k < layout[0].length; k++) {
				if(layout[i][k].getActor() != null && layout[i][k].getActor().toString().equals("player_23.png"))
					return new Posicao(k, i);
			}
		return null;
	}
	
}