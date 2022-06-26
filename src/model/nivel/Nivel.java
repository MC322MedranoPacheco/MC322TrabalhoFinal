package model.nivel;

import java.util.ArrayList;

import control.gameControl.ISolicitarMovimento;
import model.autor.IActor;
import model.autor.ICommand;
import model.item.Item;
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
				if(conexion.acao(salas[sala].getCelula(posicaoFinal).getActor(), Posicao.direcao(posicaoOrigem, posicaoFinal), salas[sala].getCelula(posicaoOrigem).getActor())) {
						salas[sala].mover(posicaoOrigem, posicaoFinal);
						ICommand acaoTerreno = new TerrenoAdapter(salas[sala].getCelula(posicaoFinal).getTerreno());
						conexion.acao(acaoTerreno, Posicao.direcao(posicaoOrigem, posicaoFinal), acaoTerreno);
						return true;
				}
				return false;
			case 1:
				return false; // aqui nao pode mover
			default:
				salas[sala].mover(posicaoOrigem, posicaoFinal); // aqui moveu
				ICommand acaoTerreno = new TerrenoAdapter(salas[sala].getCelula(posicaoFinal).getTerreno());
				conexion.acao(acaoTerreno, Posicao.direcao(posicaoOrigem, posicaoFinal), acaoTerreno);
				return true;
		}
	}


	@Override
	public void connect(ISolicitarMovimento conexion) {
		this.conexion = conexion;
		
	}


	@Override
	public Celula getCelula(Posicao posicao, int sala) {
		if(salas[sala].posicaoValida(posicao)){
			return salas[sala].getCelula(posicao);
		}
		else {
			return null;
		}
	}
	
	public Sala getSala(int i) {
		return salas[i];
	}


	@Override
	public void pegar(int sala, Posicao posicaoAtual, ArrayList<Item> inventario) {
		Item pego = null;
		System.out.println("ichegou aqui");
		if(!salas[sala].getCelula(posicaoAtual).getInventario().isEmpty())
			pego = salas[sala].getCelula(posicaoAtual).removerItem();
			inventario.add(pego);
			System.out.println("item adicionado");
	}
	
	
}
