package model.terreno;

import utilidades.Posicao;

public class Terra extends Terreno{

	@Override
	public boolean acao(String comando){
		return false; // nao faz uma acao
	}

	@Override
	public boolean acao(Posicao destino) {
		return false;
	}

	@Override
	public int getForca() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getResistencia() {
		// TODO Auto-generated method stub
		return 0;
	}

}
