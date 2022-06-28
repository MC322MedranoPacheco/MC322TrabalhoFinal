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
	private ICommand iCommand;
	private KeyListener key;
	private int nivelAtual = 0, salaAtual = 0;
	private Nivel[] niveis = new Nivel[4];
	private IFazerNivel iFazerNivel;
	private INivelView iNivelView;
	private IMainView iMainView;
	private IMenuView iMenuView;
	private IRLocked iRLocked;
	private String objetivo = "Gema";
	private int tentativas =0;
	private int totalNiveis = 2;
	
	private static final GameControl instance = new GameControl();
	
	private GameControl() {}
	
	public static GameControl getInstance() {
		return instance;
	}

	
	//conecta com ator
	@Override
	public void connect(ICommand iCommand) {
		this.iCommand = iCommand;
		
	}
	
	//responde solicitacao de acao
	@Override
	public boolean acao(ICommand actor, Posicao direcao, ICommand actorMaker) {
		if(actorMaker.getForca() > actor.getResistencia()) {
			return actor.acao(direcao, actor, actorMaker);
		}
		return false;
	}

	//manda comando ao ator
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
				if(nivelAtual == totalNiveis) {
					iMainView.setContentPane(iMenuView.getJFrameFimDeJogo().getContentPane(), null);
				}
				else {
					nivelAtual++;
					iMainView.setContentPane(iMenuView.getJFrameNextLevel("botaoProximaFase.jpg").getContentPane(), null);
					return false;
				}
			}
	
		}
		
		return retorno;
	}
	
	
	public void addKeyListener(KeyListener key) {
		this.key = key;
	}

	public void montarNiveis(int inicio) {
		for (int i = inicio; i < 3; i++) {
			niveis[i] = iFazerNivel.constroiNivel(null, "Nivel" + i, this); // Dar o path depois
			niveis[i].connect(this);
		}
	}
	

	public void start() {
		
		montarNiveis(nivelAtual);
		//Cria a GUI e junta ela com o personagem
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
