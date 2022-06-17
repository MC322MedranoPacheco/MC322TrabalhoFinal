package model.nivel;

import control.gameControl.ISolicitarMovimento;
import utilidades.Observer;
import utilidades.Posicao;
import utilidades.Subject;

public class Nivel implements INivel{
	private Sala salas[];
	private ISolicitarMovimento conexion;


	public boolean mover(int sala, Posicao posicaoOrigem, Posicao posicaoFinal, String actor) {
		switch(salas[sala].verificar(posicaoOrigem, posicaoFinal,actor)) {
			case 0:
				boolean resposta = true; // recebe resultado do outro movimento que vai ser pedido pelo controle
				if(conexion.acao(salas[sala].getCelula(posicaoFinal).getActor().toString(), posicao)) //temos que implementar metodo de saber para onde esta indo
						salas[sala].mover(posicaoOrigem, posicaoFinal, actor);
						return true;
				return false;
			case 1:
				return false; // aqui nao pode mover
			default:
				salas[sala].mover(posicaoOrigem, posicaoFinal, actor); // aqui moveu
				return true;
		}
	}


	@Override
	public void connect(ISolicitarMovimento conexion) {
		this.conexion = conexion;
		
	}


	
	
	
	
	
}
