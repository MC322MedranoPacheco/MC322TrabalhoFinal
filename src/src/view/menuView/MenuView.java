package view.menuView;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import control.gameControl.IStart;

public class MenuView implements IMenuView, ActionListener{
	private JFrame janelaMenu;
	private IStart iStart;
	
	public MenuView() {
		String diretorio = MenuView.class.getResource(".").getPath();
		
		janelaMenu = new JFrame();
		janelaMenu.setSize(999,999);
		janelaMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = janelaMenu.getContentPane();
		contentPane.setLayout(null);
		ImageIcon imagemBotaoComecar = new ImageIcon(diretorio + "botaoComecar.png");
		JButton botaoComecar = new JButton(imagemBotaoComecar);
		botaoComecar.setActionCommand("Jogar");
		botaoComecar.addActionListener(this);
		botaoComecar.setBounds(150, 666, 200, 100);
		janelaMenu.add(botaoComecar);
		
		ImageIcon imagemFechar = new ImageIcon(diretorio + "botaoFechar.png"); // Sei la se vai dar pra implementar isso
		JButton botaoFechar = new JButton(imagemFechar);
		botaoFechar.setBounds(650, 666, 200, 100);
		contentPane.setBackground(new Color(200, 240, 255));
		botaoFechar.setActionCommand("Fechar");
		botaoFechar.addActionListener(this);
		janelaMenu.add(botaoFechar);
		
	}
	public Container getContentPane() {
		return janelaMenu.getContentPane();	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
        if (command.equals("Jogar")) {
            iStart.start();	
        }
        else if(command.equals("Fechar")) {
        	System.exit(0);
        }
        	
	}
	@Override
	public void connect(IStart iStart) {
		this.iStart = iStart;
		
	}
	
	public JFrame getJFrameNextLevel(String imagem) {
	
		String diretorio = MenuView.class.getResource(".").getPath();
		janelaMenu = new JFrame();
		janelaMenu.setSize(999,999);
		janelaMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = janelaMenu.getContentPane();
		contentPane.setBackground(new Color(200, 255, 200));
		contentPane.setLayout(null);
		ImageIcon imagemBotaoComecar = new ImageIcon(diretorio + imagem);
		JButton botaoComecar = new JButton(imagemBotaoComecar);
		botaoComecar.setActionCommand("Jogar");
		botaoComecar.addActionListener(this);
		botaoComecar.setBounds(400, 666, 200, 100);
		janelaMenu.add(botaoComecar);
		
		return janelaMenu;
	}
	
	public JFrame getJFramePerdeu() {
		JFrame retorno = getJFrameNextLevel("TentarDenov.jpg");
		retorno.getContentPane().setBackground(new Color(255, 200,200));
		return retorno;
	}
	
	public JFrame getJFrameFimDeJogo() {
		String diretorio = MenuView.class.getResource(".").getPath();
		janelaMenu = new JFrame();
		janelaMenu.setSize(999,999);
		janelaMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = janelaMenu.getContentPane();
		contentPane.setBackground(new Color(200, 200, 200));
		ImageIcon imagemBotaoFim = new ImageIcon(diretorio + "BotaoFim.jpg");
		JButton botaoFim = new JButton(imagemBotaoFim);
		botaoFim.setActionCommand("Fechar");
		botaoFim.addActionListener(this);
		botaoFim.setBounds(400, 666, 200, 100);
		
		janelaMenu.add(botaoFim);
		return janelaMenu;
	}
}

