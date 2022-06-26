package model.nivel;

import java.util.ArrayList;

import model.autor.IActor;
import model.item.Item;
import utilidades.Posicao;

public interface IAction {

	public boolean mover(int sala, Posicao posicaoAtual, Posicao destino, int forca);

	public Celula getCelula(Posicao posicao, int sala);

	public void pegar(int sala, Posicao posicaoAtual, ArrayList<Item> inventario);
	
	
}
