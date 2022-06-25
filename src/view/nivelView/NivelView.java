package view.nivelView;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

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
	
	public NivelView(int x, int y, Sala sala) {
		String diretorio = NivelView.class.getResource(".").getPath();
		JFrame janelaJogo = new JFrame();
		janelaJogo.setSize(999,999);
		janelaJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		vetorJLabelAnima = new JLabelAnima[x]; //TEM QUE MUDAR ISSO
		Container contentPane = janelaJogo.getContentPane();
		contentPane.setLayout(null);
		int espacamento = CalculaEspacamento(x);
		
		matrizJLabel = new JLabel[y][x];
		
		for(int i = 0; i < y; i++) {
			for(int k = 0; k < x; k++) {
				Posicao pos = new Posicao(k, i);
				if(sala.getCelula(pos).getActor() != null) {
					ImageIcon imagemPerso = new ImageIcon(sala.getCelula(pos).getActor().toString());
					JLabelAnima jlabelP = new JLabelAnima(imagemPerso, espacamento + k*66, espacamento + i*66, 1, 0);
					jlabelP.setBounds(espacamento + k*66 , espacamento + i*66, 64, 64);
					
					System.out.println("AaA");
					contentPane.add(jlabelP);
					sala.getCelula(pos).getActor().registrarView(jlabelP);
					vetorJLabelAnima[k] = jlabelP;
				}
				//Adiciona os terrenos
				ImageIcon imagemTerreno = new ImageIcon(sala.getCelula(pos).getTerreno().toString());
				matrizJLabel[i][k] = new JLabel(imagemTerreno);
				Dimension sizeT = matrizJLabel[i][k].getPreferredSize();
				matrizJLabel[i][k].setBounds(espacamento + k*66 , espacamento + i*66, sizeT.width, sizeT.height);
				janelaJogo.add(matrizJLabel[i][k]);
			}
		}
		janelaJogo.setVisible(true);
	}
}
