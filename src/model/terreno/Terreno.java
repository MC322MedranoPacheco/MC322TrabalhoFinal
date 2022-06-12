package model.terreno;

import model.nivel.IAction;
import view.terrenoView.ITerrenoShow;

public abstract class Terreno implements ITerreno{

	protected IAction iAction;
	protected ITerrenoShow iTerrenoShow;
	private int x, y;
	

	public void connect(IAction iAction) {
		this.iAction = iAction;
	}
	
	public void connect(ITerrenoShow iTerrenoShow) {
		this.iTerrenoShow = iTerrenoShow;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}


}

