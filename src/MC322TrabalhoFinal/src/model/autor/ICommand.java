package model.autor;

import java.util.ArrayList;

import model.item.Item;
import utilidades.Posicao;

public interface ICommand extends IVivo{
	
	public boolean acao(String comando, IVivo vivo);
	public boolean acao(Posicao destino, ICommand receiver, ICommand maker);
	public int getForca();
	public int getResistencia();
	public ArrayList<Item> getInventario();
	public Posicao getPosicao();
}
