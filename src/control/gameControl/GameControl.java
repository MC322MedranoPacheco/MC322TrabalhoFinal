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
	public boolean acao(ICommand actor, Posicao direcao, int forca) {
		if(forca > actor.getResistencia()) {
			return actor.acao(direcao);
		}
		return false;
	}

	@Override
	public boolean acao(String comando) {
		return iCommand.acao(comando);
	}
	
	
	
	
	
}
