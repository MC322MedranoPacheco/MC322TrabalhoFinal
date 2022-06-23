package model.autor;

import utilidades.Posicao;

public interface IActor extends ICommand, IRAction, IRVisualActor {
	public void setPosicao(Posicao posicao);
}
