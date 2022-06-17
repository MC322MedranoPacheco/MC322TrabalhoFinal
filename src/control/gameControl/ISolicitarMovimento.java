package control.gameControl;

import model.autor.IActor;
import model.nivel.IAction;
import utilidades.Posicao;

public interface ISolicitarMovimento{
	public void acao(String actor, Posicao posicaoFinal);

	public boolean acao(IActor actor, Posicao direcao);
}
