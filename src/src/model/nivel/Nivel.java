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
	public ISolicitarMovimento conexion;
	private boolean finished = false;
	String objetivo;
	Observer obs;
	
	@Override
	public void addImage(int i, Posicao pos) {
		salas[i].getNivelView().addImage(salas[i], pos);
	}


	public boolean mover(int sala, Posicao posicaoOrigem, Posicao posicaoFinal, int forca) {
		boolean retorno;
		switch(salas[sala].verificar(posicaoFinal)) {
			case 0:	// recebe resultado do outro movimento que vai ser pedido pelo controle
				if(conexion.acao(salas[sala].getCelula(posicaoFinal).getActor(), Posicao.direcao(posicaoOrigem, posicaoFinal), salas[sala].getCelula(posicaoOrigem).getActor())) {
						salas[sala].mover(posicaoOrigem, posicaoFinal);
						ICommand acaoTerreno = new TerrenoAdapter(salas[sala].getCelula(posicaoFinal).getTerreno());
						conexion.acao(acaoTerreno, Posicao.direcao(posicaoOrigem, posicaoFinal), acaoTerreno);
						retorno = true;
				}
				retorno = false;
				break;
			case 1:
				retorno = false; // aqui nao pode mover
				break;
			default:
				salas[sala].mover(posicaoOrigem, posicaoFinal); // aqui moveu
				ICommand acaoTerreno = new TerrenoAdapter(salas[sala].getCelula(posicaoFinal).getTerreno());
				conexion.acao(acaoTerreno, Posicao.direcao(posicaoOrigem, posicaoFinal), acaoTerreno);
				retorno = true;
				break;
		}
		return retorno;
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
		if(!salas[sala].getCelula(posicaoAtual).getInventario().isEmpty()) {
			pego = salas[sala].getCelula(posicaoAtual).removerItem();
			inventario.add(pego);
			System.out.println("item adicionado");
			if(pego.getItemCode() == "goldenKey") {
				System.out.println("Mudou pra true");
				finished = true;
			}
		}
	}


	@Override
	public void removerItem(int sala, Posicao pos) {
		salas[sala].getNivelView().removeItem(salas[sala], pos);
		
	}		
	
	public boolean getFinished() {
		return finished;
	}


	@Override
	public boolean interact(int sala, Posicao posicaoAtual, ArrayList<Item> inventario) {
		Posicao[] adjacentes = new Posicao[4];
		adjacentes[0] = new Posicao(posicaoAtual.getX(), posicaoAtual.getY()+1);
		adjacentes[1] = new Posicao(posicaoAtual.getX(), posicaoAtual.getY()-1);
		adjacentes[2]= new Posicao(posicaoAtual.getX()+1, posicaoAtual.getY());
		adjacentes[3]= new Posicao(posicaoAtual.getX()-1, posicaoAtual.getY());
		for(int i = 0; i < 4; i++) {
			if(salas[sala].getCelula(adjacentes[i]) != null && salas[sala].getCelula(adjacentes[i]).getActor() != null) {
				salas[sala].getCelula(adjacentes[i]).getActor().interact(inventario);
			}
		}
		return false;
	}

}
