package control.gameControl;

import java.awt.event.KeyListener;

import control.leitor.ILeitor;
import control.leitor.IRLocked;
import control.montador.IFazerNivel;
import model.autor.ICommand;
import model.nivel.Nivel;
import utilidades.Posicao;
import view.mainView.IMainView;
import view.nivelView.ILocked;
import view.nivelView.INivelView;

public class GameControl implements IGameControl{
	ICommand iCommand;
	KeyListener key;
	int nivelAtual, salaAtual;
	Nivel[] niveis = new Nivel[4];
	IFazerNivel iFazerNivel;
	INivelView iNivelView;
	IMainView iMainView;
	
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

	public void montarNiveis() {
		for (int i = 0; i < 1; i++) {
			niveis[i] = iFazerNivel.constroiNivel(null, "Nivel" + i); // Dar o path depois
			System.out.println(niveis[i]);
			niveis[i].connect(this);
		}
	}
	
	public void rodarNivel(Nivel nivel) {
		while (nivel.getFinished()) {
			// Get update 
			// wait
		}
	}
	
	public void start() {
		montarNiveis();
		for (int i = 0; i < 1; i++) {
			//Montar o view
			rodarNivel(niveis[i]);
			//Montar uma telinha de ganhou
		}
	}

	@Override
	public void connect(IFazerNivel iFazerNivel) {
		this.iFazerNivel = iFazerNivel;
	}
	
	public void connect(INivelView iNivelView) {
		this.iNivelView = iNivelView;
	}
	
	public void connect(IMainView iMainView) {
		this.iMainView = iMainView;
	}

}
