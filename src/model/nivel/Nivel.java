package model.nivel;

import control.gameControl.ISolicitarMovimento;
import model.autor.IActor;
import model.autor.ICommand;
import model.terreno.TerrenoAdapter;
import utilidades.Observer;
import utilidades.Posicao;
import utilidades.Subject;

public class Nivel implements INivel{
	public Sala salas[];
	private ISolicitarMovimento conexion;
	
	


	public boolean mover(int sala, Posicao posicaoOrigem, Posicao posicaoFinal, int forca) {
		switch(salas[sala].verificar(posicaoFinal)) {
			case 0:	// recebe resultado do outro movimento que vai ser pedido pelo controle
				if(conexion.acao(salas[sala].getCelula(posicaoFinal).getActor(), Posicao.direcao(posicaoOrigem, posicaoFinal), forca)) {
						salas[sala].mover(posicaoOrigem, posicaoFinal);
						ICommand acaoTerreno = new TerrenoAdapter(salas[sala].getCelula(posicaoFinal).getTerreno());
						conexion.acao(acaoTerreno, Posicao.direcao(posicaoOrigem, posicaoFinal), 10000);
						return true;
				}
				return false;
			case 1:
				return false; // aqui nao pode mover
			default:
				salas[sala].mover(posicaoOrigem, posicaoFinal); // aqui moveu
				ICommand acaoTerreno = new TerrenoAdapter(salas[sala].getCelula(posicaoFinal).getTerreno());
				conexion.acao(acaoTerreno, Posicao.direcao(posicaoOrigem, posicaoFinal), 10000);
				return true;
		}
	}


	@Override
	public void connect(ISolicitarMovimento conexion) {
		this.conexion = conexion;
		
	}

	public Sala getSala(int i) {
		return salas[i];
	}	

	
}
