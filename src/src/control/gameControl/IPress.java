package control.gameControl;

import java.awt.event.KeyListener;

import control.leitor.ILeitor;
import control.leitor.IRLocked;
import view.nivelView.ILocked;

public interface IPress{
	public boolean acao(String comando);
	public void addKeyListener(KeyListener key);
}
