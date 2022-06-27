package model.autor.interactiveObjects;

import java.util.ArrayList;

import model.autor.Actor;
import model.autor.IActor;
import model.autor.ICommand;
import model.autor.IVivo;
import model.item.Item;
import model.nivel.IAction;
import utilidades.Observer;
import utilidades.Posicao;
import utilidades.Subject;

public class LaserFeixo extends Actor{
	protected int direcao;

	

	public LaserFeixo(int x, int y, int sala, IAction iaction) {
		super(x,y, iaction);
		this.direcao = direcao;
		forca = 0;
		resistencia = 0;
		
		
	}

	@Override
	public boolean acao(String comando, IVivo vivo) {
		return false;
	}

	@Override
	public boolean acao(Posicao destino, ICommand vivo, ICommand receiver) {
		vivo.setVivo(false);
		iaction.getCelula(posicaoAtual, sala).remover(true);
		return true;
	}

	
	
	@Override
	public String toString() {
		return LaserFeixo.class.getResource(".").getPath() + "laserRedVertical.png";
	}

	@Override
	public ArrayList<Item> getInventario() {
		return null;
	}

	@Override
	public void setVivo(boolean vivo) {
	}

	@Override
	public boolean getVivo() {
		return false;
	}

	


}
