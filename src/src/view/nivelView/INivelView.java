package view.nivelView;

import java.awt.Container;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import model.nivel.Sala;
import utilidades.Posicao;

public interface INivelView {
	public Container getContentPane();
	public JFrame getJFrame();
	public JFrame geraJFrame(int x, int y, Sala sala, KeyListener key);
	public void addImage(Sala sala, Posicao pos);
	public void removeItem(Sala sala, Posicao pos);
	public JLabelAnima getPersonagem();
}
