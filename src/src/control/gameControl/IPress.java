package control.gameControl;

import java.awt.event.KeyListener;

public interface IPress{
	public boolean acao(String comando);
	public void addKeyListener(KeyListener key);
}
