package model.autor.interactiveObjects;

import model.autor.Actor;
import model.autor.ICommand;
import model.autor.IVivo;
import model.nivel.IAction;
import utilidades.Posicao;

public abstract class Porta extends Actor{
	protected int sentido;
	protected boolean chave;

	public Porta(int x, int y, IAction iaction, int sentido) {
		super(x, y, iaction);
		this.sentido = sentido;
		chave = false;
		forca = 1;
		resistencia = 1;
	}
	
	
	@Override
	public String toString() {
		String retorno;
		if(!chave) {
			if(sentido == 0)
				retorno = "portaFechada";
			else
				retorno = "portaFechadaVertical";
			}
		else {
			if(sentido == 0)
				retorno = "portaAberta";
			else
				retorno = "portaAbertaVertical";
		}
		return (KeyPorta.class.getResource(".").getPath() + retorno + ".png");
	}
	
	
	@Override
	public boolean acao(String comando, IVivo vivo) {
		return false;
	}
	
	@Override
	public boolean acao(Posicao destino, ICommand vivo, ICommand receiver) {
		Posicao segundoDestino = Posicao.direcao(receiver.getPosicao(), destino);
		iaction.mover(sala, receiver.getPosicao(), destino, receiver.getForca());
		iaction.mover(sala, receiver.getPosicao(), segundoDestino, receiver.getForca());
		return false;
	}
	
	
	@Override
	public void setVivo(boolean vivo) {
	}

	@Override
	public boolean getVivo() {
		return false;
	}

	


	
	
	
	
	
	

}
