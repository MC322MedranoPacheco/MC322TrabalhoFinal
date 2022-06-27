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
	JLabel[][] matrizItens;
	JLabelAnima[][] matrizJLabelAnima;
	JFrame janelaJogo;
	
	private int CalculaEspacamento(int x) {
		return (1000 - 69*x)/2;
	}
	
	public JFrame geraJFrame(int x, int y, Sala sala, KeyListener key) {
		String diretorio = NivelView.class.getResource(".").getPath();
		janelaJogo = new JFrame();
		janelaJogo.setSize(999,999);
		janelaJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		matrizJLabelAnima = new JLabelAnima[y][x]; //TEM QUE MUDAR ISSO
		Container contentPane = janelaJogo.getContentPane();
		contentPane.setLayout(null);
		int espacamento = CalculaEspacamento(x);
		janelaJogo.addKeyListener(key);
		
		sala.setNivelView(this);
		matrizJLabel = new JLabel[y][x];
		matrizItens = new JLabel[y][x];
		
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
					matrizJLabelAnima[i][k] = jlabelP;
				}
				
				if(sala.getCelula(pos).getInventario().size() != 0) {
					for(int j = 0; j < sala.getCelula(pos).getInventario().size(); j++) {
						ImageIcon imagemItem = new ImageIcon(sala.getCelula(pos).getInventario().get(j).toString());
						matrizItens[i][k] = new JLabel(imagemItem);
						System.out.println(imagemItem);
						matrizItens[i][k].setBounds(espacamento + k*66 , espacamento + i*66, 64, 64);
						janelaJogo.add(matrizItens[i][k]);
					}
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
				if(matrizJLabelAnima[i][k] != null) {
					contentPane.setComponentZOrder(matrizJLabelAnima[i][k], 0);
					}
			}
		}
		
		return janelaJogo;

	}
	public JLabelAnima getPersonagem() {
		for(int i = 0; i < matrizJLabelAnima.length; i++) {
			for(int k = 0; k < matrizJLabelAnima[0].length; k++) {
			if(matrizJLabelAnima[i][k].getIcon().toString() == "/C:/Users/gmedr/Documents/MC322TrabalhoFinal/bin/model/autor/personagens/player_23.png");{
				
				matrizJLabelAnima[i][k].setNivelView(this);
				return matrizJLabelAnima[i][k];
				}
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
	
	public void addImage(Sala sala, Posicao pos) {
		int espacamento = CalculaEspacamento(sala.getTamanho());
		ImageIcon imagemPerso = new ImageIcon(sala.getCelula(pos).getActor().toString());
		JLabelAnima jlabelP = new JLabelAnima(imagemPerso, espacamento + pos.getX()*66, espacamento + pos.getY()*66, 1, 0);
		jlabelP.setBounds(espacamento + pos.getX()*66 , espacamento + pos.getY()*66, 64, 64);
		janelaJogo.getContentPane().add(jlabelP);
		sala.getCelula(pos).getActor().registrarView(jlabelP);
		jlabelP.setSubject(sala.getCelula(pos).getActor());
		matrizJLabelAnima[pos.getY()][pos.getX()] = jlabelP;
		
		for (int i = 0; i < matrizJLabelAnima.length; i++) {
			for(int k = 0; k < matrizJLabelAnima[0].length; k++) {
				if(matrizJLabelAnima[i][k] != null) {
					janelaJogo.getContentPane().setComponentZOrder(matrizJLabelAnima[i][k], 0);
					}
			}
		
		}
		janelaJogo.getContentPane().repaint();
	}
	
	public void removeItem(Sala sala, Posicao pos) {
		if(matrizItens[pos.getY()][pos.getX()] != null) {
			janelaJogo.getContentPane().remove(matrizItens[pos.getY()][pos.getX()]);
			janelaJogo.getContentPane().repaint();
		}
	}
}