package view.menuView;

import javax.swing.JFrame;

import view.mainView.IRStart;

public interface IMenuView extends IRStart{
	public JFrame getJFrameNextLevel(String string);
	public JFrame getJFramePerdeu();
	public JFrame getJFrameFimDeJogo();
}
