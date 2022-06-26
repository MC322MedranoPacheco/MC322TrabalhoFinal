package control.gameControl;

import model.autor.IActor;
import model.autor.ICommand;
import utilidades.Posicao;

public class GameControl implements IGameControl{
	ICommand iCommand;
	
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
	
	
	
	
	
}
