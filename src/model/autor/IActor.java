package model.autor;

import utilidades.Posicao;

public interface IActor extends ICommand, IRAction, IRVisualActor, ActorSubjectView {
	public void setPosicao(Posicao posicao);
}
