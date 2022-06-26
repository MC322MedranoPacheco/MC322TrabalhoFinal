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
		botaoComecar.setActionCommand("Teste");
		botaoComecar.addActionListener(this);
		botaoComecar.setBounds(150, 666, 200, 100);
		janelaMenu.add(botaoComecar);
		
		ImageIcon imagemBotaoCarregar = new ImageIcon(diretorio + "botaoCarregar.png"); // Sei la se vai dar pra implementar isso
		JButton botaoCarregar = new JButton(imagemBotaoCarregar);
		botaoCarregar.setBounds(650, 666, 200, 100);
		contentPane.setBackground(new Color(200, 240, 255));
		janelaMenu.add(botaoCarregar);
		
	}
	public Container getContentPane() {
		return janelaMenu.getContentPane();	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
        if (command.equals("Teste")) {
            iStart.start();
			
        }
	}
	@Override
	public void connect(IStart iStart) {
		this.iStart = iStart;
		
	}	
}

