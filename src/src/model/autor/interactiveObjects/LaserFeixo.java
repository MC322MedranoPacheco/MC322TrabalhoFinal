package model.autor.interactiveObjects;

import java.util.ArrayList;

import exceptions.InvalidParameterException;
import model.autor.Actor;
import model.autor.ICommand;
import model.autor.IVivo;
import model.item.Item;
import model.nivel.IAction;
import utilidades.Posicao;

public class LaserFeixo extends Actor{
	protected int direcao;

	

	public LaserFeixo(int x, int y, int sala, IAction iaction, int direcao) {
		super(x,y, iaction);
		this.direcao = direcao;
		forca = 0;
		resistencia = 0;
		
		
	}

	@Override
	public boolean acao(String comando, IVivo vivo) {
		return false;
	}
	
	public int getDirecao() {
		return direcao;
	}

	@Override
	public boolean acao(Posicao destino, ICommand vivo, ICommand receiver) {
		receiver.setVivo(false);
		iaction.getCelula(posicaoAtual, sala).remover(true);
		return true;
	}

	
	
	@Override
	public String toString() {
		try {
		if(direcao == 0 || direcao == 1)
			return LaserFeixo.class.getResource(".").getPath() + "laserRedVertical.png";
		else if(direcao == 2 || direcao == 3)
			return LaserFeixo.class.getResource(".").getPath() + "laserRedHorizontal.png";
		else {
			throw new InvalidParameterException("direcao invalida");
		}
		}
		catch(InvalidParameterException e) {
			e.getMessage();
			e.printStackTrace();
		}
		return null;
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

	@Override
	public Posicao getPosicaoAnterior() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean interact(ArrayList<Item> inventario) {
		// TODO Auto-generated method stub
		return false;
	}

	


}
