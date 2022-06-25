package model.nivel;

import model.autor.IActor;
import utilidades.Posicao;

public interface IAction {

	public boolean mover(int sala, Posicao posicaoAtual, Posicao destino, int forca);
}
