package control.gameControl;

import model.autor.IActor;
import model.autor.ICommand;
import model.nivel.IAction;
import utilidades.Posicao;

public interface ISolicitarMovimento{

	public boolean acao(ICommand actor, Posicao direcao, int forca);
}
