package model.autor.interactiveObjects;

import java.util.ArrayList;

import model.autor.ICommand;
import model.autor.IVivo;
import model.autor.personagens.Garoto;
import model.item.Item;
import model.nivel.IAction;
import utilidades.Posicao;

public class KeyPorta extends Porta{
	private String keycode;

	public KeyPorta(int x, int y,IAction iaction, String keycode, int sentido) {
		super(x, y, iaction, sentido);
		this.keycode = keycode;
		chave = false;
	}

	@Override
	public boolean acao(Posicao destino, ICommand vivo, ICommand receiver) {
		
		if(!getLocked(receiver.getInventario())) {
			super.acao(destino, vivo, receiver);
		}
		return false;
	}

	protected boolean getLocked(ArrayList<Item> inventario) {
		boolean locked = true;
		Item achou = null;
		for(Item i : inventario) {
			System.out.println(i.getItemCode() + " " + keycode);
			if(i.getItemCode().equals(keycode)) {
				System.out.println("passou do if");
				locked = false;
				achou = i;
				break;
			}
		}
		if(!locked) {
			chave = true;
			inventario.remove(achou);
			this.setChanged(true);
			this.notificarObservadoresView("atualizar");
			System.out.println("passa aqui");
		}
		return locked;
	}


	@Override
	public ArrayList<Item> getInventario() {
		return null;
	}
	
	
	
}
