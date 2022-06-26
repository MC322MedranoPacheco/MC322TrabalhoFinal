package view.nivelView;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.autor.ActorSubjectView;
import model.nivel.Sala;
import utilidades.Posicao;

public class NivelView implements INivelView{
	
	JLabel[][] matrizJLabel;
	JLabelAnima[] vetorJLabelAnima;
	JFrame janelaJogo;
	
	private int CalculaEspacamento(int x) {
		return (1000 - 69*x)/2;
	}
	
	public NivelView(int x, int y, Sala sala, KeyListener key) {
		String diretorio = NivelView.class.getResource(".").getPath();
		janelaJogo = new JFrame();
		janelaJogo.setSize(999,999);
		janelaJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		vetorJLabelAnima = new JLabelAnima[x]; //TEM QUE MUDAR ISSO
		Container contentPane = janelaJogo.getContentPane();
		contentPane.setLayout(null);
		int espacamento = CalculaEspacamento(x);
		janelaJogo.addKeyListener(key);
		
		matrizJLabel = new JLabel[y][x];
		
		for(int i = 0; i < y; i++) {
			for(int k = 0; k < x; k++) {
				Posicao pos = new Posicao(k, i);
				if(sala.getCelula(pos).getActor() != null) {
					ImageIcon imagemPerso = new ImageIcon(sala.getCelula(pos).getActor().toString());
					JLabelAnima jlabelP = new JLabelAnima(imagemPerso, espacamento + k*66, espacamento + i*66, 1, 0);
					jlabelP.setBounds(espacamento + k*66 , espacamento + i*66, 64, 64);
					
					contentPane.add(jlabelP);
					sala.getCelula(pos).getActor().registrarView(jlabelP);
					jlabelP.setSubject(sala.getCelula(pos).getActor());
					vetorJLabelAnima[k] = jlabelP;
				}
				//Adiciona os terrenos
				ImageIcon imagemTerreno = new ImageIcon(sala.getCelula(pos).getTerreno().toString());
				matrizJLabel[i][k] = new JLabel(imagemTerreno);
				matrizJLabel[i][k].setBounds(espacamento + k*66 , espacamento + i*66, 64, 64);
				janelaJogo.add(matrizJLabel[i][k]);
			}
		}
		for(int i = 0; i < y; i++) {
			for(int k = 0; k < x; k++) {
				contentPane.setComponentZOrder(matrizJLabel[i][k], x*y);
			}
		}
		for (int i = 0; i < vetorJLabelAnima.length; i++) {
			if(vetorJLabelAnima[i] != null) {
				contentPane.setComponentZOrder(vetorJLabelAnima[i], 0);
				}
		}

	}
	public JLabelAnima getPersonagem() {
		for(int i = 0; i < vetorJLabelAnima.length; i++) {
			if(vetorJLabelAnima[i].getIcon().toString() == "/C:/Users/gmedr/Documents/MC322TrabalhoFinal/bin/model/autor/personagens/player_23.png");{
				System.out.println("AaAa");
				vetorJLabelAnima[i].setNivelView(this);
				return vetorJLabelAnima[i];
			}
		}
		return null;
	}
	
	public Container getContentPane() {
		return janelaJogo.getContentPane();
	}
	
	public JFrame getJFrame() {
		return janelaJogo;
	}
}