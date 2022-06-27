package view.menuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

public class BotaoInicia extends JButton implements ActionListener{

	
	public BotaoInicia(ImageIcon imagem) {
		super(imagem);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
        if (command.equals("Teste")) {
            System.out.println("aAaAaAa");
		
        }
	}
}
