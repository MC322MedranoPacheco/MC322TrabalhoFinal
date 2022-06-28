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
import view.menuView.IMenuView;
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
	IMenuView iMenuView;
	IRLocked iRLocked;
	String objetivo = "";
	private int tentativas =0;
	
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

		boolean retorno = iCommand.acao(comando, iCommand);
		if(!iCommand.getVivo()) {
			tentativas++;
			iMainView.setContentPane(iMenuView.getJFramePerdeu().getContentPane(), null);
			return false;
		}
			
		for(int i = 0; i < iCommand.getInventario().size(); i++) {
			if(iCommand.getInventario().get(i).getItemCode().equals(objetivo)) {
				nivelAtual++;
				iMainView.setContentPane(iMenuView.getJFrameNextLevel().getContentPane(), null);
				return false;
			}
		}
		
		return retorno;
	}
	
	public void addKeyListener(KeyListener key) {
		this.key = key;
	}

	public void montarNiveis() {
		for (int i = 0; i < 2; i++) {
			System.out.println("aaaAAAaaa");
			niveis[i] = iFazerNivel.constroiNivel(null, "Nivel" + i, this); // Dar o path depois
			niveis[i].connect(this);
		}
	}
	

	public void start() {
		if(nivelAtual == 0 && salaAtual == 0 && tentativas == 0) {
			montarNiveis();
		}
		this.connect(niveis[nivelAtual].salas[salaAtual].getCelula(niveis[nivelAtual].salas[salaAtual].getPosPersonagem()).getActor()); // Mudar isso depois
		niveis[nivelAtual].connect(this);
		if(nivelAtual == 0 && salaAtual == 0 && tentativas == 0) {
			iNivelView.geraJFrame(niveis[nivelAtual].salas[salaAtual].getTamanho(), niveis[nivelAtual].salas[salaAtual].getTamanho(), niveis[nivelAtual].salas[salaAtual], key);
			iMainView.setContentPane(iNivelView.getContentPane(), iNivelView.getJFrame().getKeyListeners()[0]);
		}
		else { //Para evitar duplo clicks
			iNivelView.geraJFrame(niveis[nivelAtual].salas[salaAtual].getTamanho(), niveis[nivelAtual].salas[salaAtual].getTamanho(), niveis[nivelAtual].salas[salaAtual], null);
			iMainView.setContentPane(iNivelView.getContentPane(), null);
		}
		iRLocked.connect(iNivelView.getPersonagem());
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
	
	public void connect(IMenuView iMenuView) {
		this.iMenuView = iMenuView;
	}

	@Override
	public void trocarSala(int sala) {
		salaAtual = sala;
		start();
	}

}
