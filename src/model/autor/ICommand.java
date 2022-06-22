package model.autor;

import utilidades.Posicao;

public interface ICommand {
	public boolean acao(String comando);
	public boolean acao(Posicao destino);
	public int getForca();
	public int getResistencia();
}
