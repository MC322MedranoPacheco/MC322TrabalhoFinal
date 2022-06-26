package view.mainView;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class MainView implements IMainView {

	JFrame jFrameJogo;
	
	public MainView() {
		jFrameJogo = new JFrame();
		jFrameJogo.setSize(999,999);
		jFrameJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrameJogo.setVisible(true);
	}
	public void setContentPane(Container contentPane, KeyListener key) {
		jFrameJogo.setContentPane(contentPane);
		jFrameJogo.setVisible(true);
		jFrameJogo.addKeyListener(key);
		jFrameJogo.setBounds(0, 0, 1000, 1000);
		jFrameJogo.setState(Frame.ICONIFIED);
		jFrameJogo.setState(Frame.NORMAL);
		jFrameJogo.setState(Frame.NORMAL);
	}

}
