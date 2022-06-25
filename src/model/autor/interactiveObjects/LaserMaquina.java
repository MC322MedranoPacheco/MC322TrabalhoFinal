package model.autor.interactiveObjects;

import model.autor.Actor;
import model.autor.IActor;
import model.autor.ICommand;
import model.nivel.IAction;
import utilidades.Posicao;

public class LaserMaquina extends Actor{
	int direcao;

	public LaserMaquina(int x, int y,int direcao) {
		super(x, y);
		this.direcao = direcao;
		switch(direcao){
		case 0:
			if(iaction.getCelula(new Posicao(x+1,y)).getActor() == null) {
				IActor feixo = new LaserFeixo(x +1,y, direcao);
				feixo.connect(iaction);
				iaction.getCelula(new Posicao(x+1,y)).setActor(feixo);
			}
			break;
		case 1:
			
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		}
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
		return "LM";
	}

}
