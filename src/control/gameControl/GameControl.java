package control.gameControl;

import java.awt.event.KeyListener;

import control.montador.IFazerNivel;
import model.autor.ICommand;
import model.nivel.Nivel;
import utilidades.Posicao;
import view.nivelView.INivelView;

public class GameControl implements IGameControl{
	ICommand iCommand;
	KeyListener key;
	int nivelAtual;
	Nivel[] niveis = new Nivel[4];
	IFazerNivel iFazerNivel;
	
	private static final GameControl instance = new GameControl();
	
	private GameControl() {}
	
	public static GameControl getInstance() {
		return instance;
	}

	@Override
	public void connect(ICommand iCommand) {
		this.iCommand = iCommand;
		
	}
	
	@Override
	public boolean acao(ICommand actor, Posicao direcao, ICommand actorMaker) {
		if(actorMaker.getForca() > actor.getResistencia()) {
			return actor.acao(direcao, actor, actorMaker);
		}
		return false;
	}

	@Override
	public boolean acao(String comando) {
		return iCommand.acao(comando, iCommand);
	}
	
	public void addKeyListener(KeyListener key) {
		this.key = key;
	}

	public void start() {
		System.out.println("KKKKKKKKKKKK omegalul");
		for (int i = 0; i < 1; i++) {
			niveis[i] = iFazerNivel.constroiNivel(null, "Nivel" + i); // Dar o path depois
		}
		for(int i = 0; i < 1; i++) {
			
		}
	}

	@Override
	public void connect(IFazerNivel iFazerNivel) {
		this.iFazerNivel = iFazerNivel;
	}
	
}
