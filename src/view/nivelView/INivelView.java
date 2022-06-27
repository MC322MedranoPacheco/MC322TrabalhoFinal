package view.nivelView;

import java.awt.Container;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import model.nivel.Sala;

public interface INivelView {
	public Container getContentPane();
	public JFrame getJFrame();
	public JFrame geraJFrame(int x, int y, Sala sala, KeyListener key);
}
