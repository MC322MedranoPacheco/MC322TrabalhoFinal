package control.leitor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import control.gameControl.IPress;
import view.nivelView.ILocked;

public class Leitor implements ILeitor, KeyListener{
	
	IPress iPress;
	ILocked iLocked;
	
	public Leitor() {}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
	
	}

	public void keyReleased(KeyEvent e) {
		if(!iLocked.getLocked()) {
			int tecla = e.getKeyCode();
			if (tecla == KeyEvent.VK_W) {
				if(iPress.acao("w"))
					iLocked.setLocked(true);
			}
			else if (tecla == KeyEvent.VK_S) {	
				if(iPress.acao("s"))
					iLocked.setLocked(true);
			}
			else if (tecla == KeyEvent.VK_D) {
				System.out.println(tecla);
				if(iPress.acao("d"))
					iLocked.setLocked(true);
			}
			else if (tecla == KeyEvent.VK_A) {
				if(iPress.acao("a"))
					iLocked.setLocked(true);
			}
			else if(tecla == KeyEvent.VK_P) {
				iPress.acao("p");
			}
		}
	}
	
	public void connect(IPress iPress) {
		this.iPress = iPress;
	}
	@Override
	public void connect(ILocked iLocked) {
		this.iLocked = iLocked;
		
	}
	
	
}