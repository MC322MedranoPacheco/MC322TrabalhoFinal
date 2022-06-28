package model.autor.interactiveObjects;

import control.gameControl.SalaChanger;
import model.autor.Actor;
import model.autor.IActor;
import model.autor.ICommand;
import model.nivel.IAction;
import utilidades.Posicao;

public class ConnectionKeyPorta extends KeyPorta implements SalaConector{
	Posicao inicial;
	SalaChanger changer;

	public ConnectionKeyPorta(int x, int y, IAction iaction, String keycode, int sentido, int inicialX, int inicialY, SalaChanger changer) {
		super(x, y, iaction, keycode, sentido);
		inicial = new Posicao(inicialX, inicialY);
		this.changer = changer;
	}
	
	@Override
	public boolean acao(Posicao destino, ICommand vivo, ICommand receiver) {
		boolean passou;
		super.acao(destino, vivo, receiver);
		if(chave) {
			makeConection();
		}
		return false;
	}

	@Override
	public void makeConection() {
		this.sala++;
		changer.trocarSala(sala);
	}
}
