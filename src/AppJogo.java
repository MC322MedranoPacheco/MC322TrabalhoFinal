import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import control.gameControl.GameControl;
import control.leitor.Leitor;
import control.montador.Montador;
import model.nivel.Nivel;
import model.nivel.Sala;
import model.terreno.Pedra;
import model.terreno.Terreno;
import utilidades.Posicao;
import view.mainView.MainView;
import view.menuView.MenuView;
import view.nivelView.NivelView;

public class AppJogo {

	public static void main(String[] args) {
		Montador montador = new Montador();
		Nivel nivel = montador.constroiNivel(null, "Nivel0");
		Leitor leitor = new Leitor();
		
		MenuView menuV = new MenuView();
		NivelView nivelV = new NivelView(14, 14, nivel.getSala(0), leitor);
		MainView mainV = new MainView();
		System.out.println(nivelV.getJFrame().getKeyListeners().length);
		
		GameControl teste = GameControl.getInstance();
		teste.connect(nivel.salas[0].getCelula(new Posicao(0,0)).getActor());
		
		mainV.setContentPane(menuV.getContentPane(), null);
		mainV.connect(teste);
		menuV.connect(teste);
		teste.connect(montador);
		
		try {Thread.sleep(100);}catch(Exception exc) {}
		
		mainV.setContentPane(nivelV.getContentPane(), nivelV.getJFrame().getKeyListeners()[0]);
		
		nivel.connect(teste);
		leitor.connect(teste);
		teste.addKeyListener(leitor);
		leitor.connect(nivelV.getPersonagem());
		
		nivel.salas[0].getCelula(new Posicao(6,3));
				
	}
}
