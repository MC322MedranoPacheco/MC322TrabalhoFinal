package model.autor;

import model.nivel.IAction;
import view.autorView.IVisualActor;

public abstract class Actor implements IActor{
	protected IAction iaction;
	protected IVisualActor ivisualactor;
	private int x, y;
	

	public void connect(IAction iaction) {
		this.iaction = iaction;
	}
	
	public void connect(IVisualActor ivisualactor) {
		this.ivisualactor = ivisualactor;
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
