package model.autor.interactiveObjects;

import java.util.ArrayList;

import model.autor.Actor;
import model.autor.IActor;
import utilidades.Observer;
import utilidades.Posicao;
import utilidades.Subject;

public class LaserFeixo extends Actor implements Observer{
	protected int direcao;
	protected Subject anterior;
	protected Subject proximo;
	

	public LaserFeixo(int x, int y, int direcao, int sala) {
		super(x,y);
		this.direcao = direcao;
		this.sala = sala;
		gerarFeixo(direcao);
		
	}

	@Override
	public boolean acao(String comando) {
		return false;
	}

	@Override
	public boolean acao(Posicao destino) {
		return false;
	}

	@Override
	public String toString() {
		return "L" + direcao;
	}


	@Override
	public void update() {
		boolean anteriorAtivo = (boolean) anterior.getUpdate(this);
		boolean proximoAtivo = (boolean) proximo.getUpdate(this);
		if(anteriorAtivo == false) {
			autodestruir();
		}
		if(proximoAtivo == false) {
			gerarFeixo(direcao);
		}
		
		
		
	}

	protected void gerarFeixo(int direcao) {
		Posicao pos;
		switch(direcao){
		case 0:
			pos = new Posicao(posicaoAtual.getX()+1,posicaoAtual.getY());
			break;
		case 1:
			pos = new Posicao(posicaoAtual.getX()-1,posicaoAtual.getY());
			break;
		case 2:
			pos = new Posicao(posicaoAtual.getX(), posicaoAtual.getY()+1);
			break;
		case 3:
			pos = new Posicao(posicaoAtual.getX(),posicaoAtual.getY()-1);
			break;
		default:
			pos = null;
		}
		if(iaction.getCelula(pos, this.sala)!=null && iaction.getCelula(pos, this.sala).getActor()  == null) {
			IActor feixo = new LaserFeixo(pos.getX(), pos.getY(), direcao, this.sala);															
			feixo.connect(iaction);
			iaction.getCelula(pos,this.sala).setActor(feixo);
		}
		else if(iaction.getCelula(pos, this.sala).getActor().toString().charAt(0) == 'L') {
			iaction.getCelula(pos, this.sala).remover();
			
			
			
		}
		
	}

	private void autodestruir() {
		iaction.getCelula(posicaoAtual, sala).remover(); // tem que destruir direito
	}

	@Override
	public void setSubejects(Subject[] sub) {
		anterior = sub[0];
		proximo = sub[1];
	}



}
