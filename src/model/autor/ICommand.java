package model.autor;

import utilidades.Posicao;

public interface ICommand extends IVivo{
	public boolean acao(String comando, IVivo vivo);
	public boolean acao(Posicao destino, IVivo vivo);
	public int getForca();
	public int getResistencia();
}
