package model.autor;

import utilidades.Posicao;

public interface IActor extends ICommand, IRAction, IRVisualActor, IVivo, ActorSubjectView {
	public void setPosicao(Posicao posicao);
}
