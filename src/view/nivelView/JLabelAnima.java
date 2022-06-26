package view.nivelView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.autor.ActorSubjectView;



public class JLabelAnima extends JLabel implements ActionListener, ObserverActor{
	
	private static final long serialVersionUID = 4265344773882789137L;
	
	ActorSubjectView sub;
	int x, y, shiftX, shiftY;
	public JLabelAnima(ImageIcon imagem, int x, int y, int shiftX, int shiftY) {
		super(imagem);
		this.x = x;
		this.y = y;
		setLocation(x, y);
		this.shiftX = shiftX;
		this.shiftY = shiftY;
	}
	
	public void mover() { 
		x += shiftX;
		y += shiftY;
		setLocation(x, y);
	}
	
	public void actionPerformed(ActionEvent e) {
		mover();
	}

	@Override
	public void update(String direcao) {
		Metronomo metro = new Metronomo(16, 66);
		switch (direcao) {
		case "w":
			this.shiftX = 0;
			this.shiftY = -1;
			break;
		case "a":
			this.shiftX = -1;
			this.shiftY = 0;
			break;
		case "s":
			this.shiftX = 0;
			this.shiftY = 1;
			break;
		case "d":
			this.shiftX = 1;
			this.shiftY = 0;
			break;
		case "r":
			ImageIcon imagemInexistente = new ImageIcon("essa imagem nao existe");
            setIcon(imagemInexistente);
            return;
		case "atualizar":
			ImageIcon atualizada = new ImageIcon(sub.toString());
			setIcon(atualizada);
			return;
		}
		anima();
	}

	@Override
	public void setSubject(ActorSubjectView sub) {
		this.sub = sub;
		
	}
	
	public void anima() {
		Metronomo metro = new Metronomo(8, 66);
		metro.addActionListener(this);
		metro.start();
	}
}