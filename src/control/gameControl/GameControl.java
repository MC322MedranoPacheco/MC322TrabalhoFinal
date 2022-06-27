package control.gameControl;

import java.awt.event.KeyListener;

import control.leitor.ILeitor;
import control.leitor.IRLocked;
import control.montador.IFazerNivel;
import model.autor.ICommand;
import model.nivel.Nivel;
import utilidades.Observer;
import utilidades.Posicao;
import utilidades.Subject;
import view.mainView.IMainView;
import view.nivelView.ILocked;
import view.nivelView.INivelView;

public class GameControl implements IGameControl{
	ICommand iCommand;
	KeyListener key;
	int nivelAtual = 0, salaAtual = 0;
	Nivel[] niveis = new Nivel[4];
	IFazerNivel iFazerNivel;
	INivelView iNivelView;
	IMainView iMainView;
	IRLocked iRLocked;
	String objetivo = "goldenKey";
	
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
		System.out.println(iCommand.getInventario());
		
		for(int i = 0; i < iCommand.getInventario().size(); i++) {
			System.out.println(iCommand.getInventario().get(i).getItemCode());
			if(iCommand.getInventario().get(i).getItemCode().equals(objetivo)) {
				nivelAtual++;
				start();
				return false;
			}
		}
		
		return iCommand.acao(comando, iCommand);
	}
	
	public void addKeyListener(KeyListener key) {
		this.key = key;
	}

	public void montarNiveis() {
		for (int i = 0; i < 2; i++) {
			niveis[i] = iFazerNivel.constroiNivel(null, "Nivel" + i); // Dar o path depois
			niveis[i].connect(this);
		}
	}
	

	public void start() {
		if(nivelAtual == 0) {
			montarNiveis();
		}
		this.connect(niveis[nivelAtual].salas[salaAtual].getCelula(new Posicao(0,0)).getActor()); // Mudar isso depois
		niveis[nivelAtual].connect(this);
		iNivelView.geraJFrame(niveis[nivelAtual].salas[salaAtual].getTamanho(), niveis[nivelAtual].salas[salaAtual].getTamanho(), niveis[nivelAtual].salas[salaAtual], key);
		iRLocked.connect(iNivelView.getPersonagem());
		iMainView.setContentPane(iNivelView.getContentPane(), iNivelView.getJFrame().getKeyListeners()[0]);
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
	
	public void connect(IRLocked iRLocked) {
		this.iRLocked = iRLocked;
	}

}
