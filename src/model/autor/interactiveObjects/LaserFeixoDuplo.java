package model.autor.interactiveObjects;

import model.nivel.IAction;
import utilidades.Subject;

public class LaserFeixoDuplo extends LaserFeixo{
	private int segundaDirecao;
	private Subject segundoAnterior;
	private Subject segundoProximo;

	public LaserFeixoDuplo(int x, int y, int direcao,int segundaDirecao, int sala, IAction iaction) {
		super(x, y, direcao, sala, iaction);
		gerarFeixo(segundaDirecao);
		Subject[] subs = new Subject[2];
		subs[0] = iaction.getCelula(getPosAnterior(posicaoAtual, segundaDirecao), sala); 
		subs[1] = iaction.getCelula(getPosAnterior(posicaoAtual, segundaDirecao), sala);
		setSegundosSubjects(subs);
	}
	
	@Override
	public void autodestruir(int direcao) {
		if(direcao == this.direcao) {
			super.autodestruir(direcao);
			gerarFeixo(this.segundaDirecao);
		}
		else if(direcao == this.segundaDirecao) {
			super.autodestruir(segundaDirecao);
			gerarFeixo(this.direcao);
		}
		else {
			//erro
		}
		
	}

	
	
	public void setSegundosSubjects(Subject[] sub) {
		segundoAnterior = sub[0];
		segundoProximo = sub[1];
	}
	
	public void setSegundoSubject(Subject sub) {
		segundoAnterior = sub;
	}
	
	@Override
	public void update() {
		super.update();
		if((boolean) segundoAnterior.getUpdate(this)) { 
			autodestruir(segundaDirecao);
		}
		if(!(boolean) segundoProximo.getUpdate(this)){
			gerarFeixo(segundaDirecao);
		}
	}
	

}
