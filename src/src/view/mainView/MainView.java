package view.mainView;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import control.gameControl.IStart;
import view.nivelView.INivelShow;

public class MainView implements IMainView {

	JFrame jFrameJogo;
	private IStart iStart;
	private INivelShow iNivelShow;
	
	public MainView() {
		jFrameJogo = new JFrame();
		jFrameJogo.setSize(999,999);
		jFrameJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void setContentPane(Container contentPane, KeyListener key) {
		jFrameJogo.setContentPane(contentPane);
		jFrameJogo.setVisible(true);
		jFrameJogo.addKeyListener(key);
		jFrameJogo.setBounds(0, 0, 1000, 1000);
		jFrameJogo.setState(Frame.ICONIFIED);
		try{Thread.sleep(100);}catch(Exception exc) {}
		jFrameJogo.setState(Frame.NORMAL);
	}
	
	public void connect(IStart iStart) {
		this.iStart = iStart;
	}
	
	public void connect(INivelShow iNivelShow) {
		this.iNivelShow = iNivelShow;
		
	}	
	public void show() {
		jFrameJogo.setVisible(true);
	}
}
