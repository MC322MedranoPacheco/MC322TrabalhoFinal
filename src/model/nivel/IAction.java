package model.nivel;

import utilidades.Posicao;

public interface IAction {
	public boolean mover(int sala,Posicao origem, Posicao destino, String actor);
}
