package view.nivelView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.autor.ActorSubjectView;


public class JLabelAnima extends JLabel implements ActionListener, ObserverActor, ILocked{
	
	private static final long serialVersionUID = 4265344773882789137L;
	
	ActorSubjectView sub;
	int x, y, shiftX, shiftY, contador = 0;
	private static boolean locked = false;
	NivelView nivelView;
	
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
		locked = true;
		contador += Math.abs(shiftX) + Math.abs(shiftY);
		setLocation(x, y);
		if(contador == 66) {
			locked = false;
			contador = 0;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		mover();
	}

	@Override
	public void update(String direcao) {
		if(!locked) {
			switch (direcao) {
			case "w":
				this.shiftX = 0;
				this.shiftY = -2;
				break;
			case "a":
				this.shiftX = -2;
				this.shiftY = 0;
				break;
			case "s":
				this.shiftX = 0;
				this.shiftY = 2;
	
				break;
			case "d":
				this.shiftX = 2;
				this.shiftY = 0;
	
				break;
			case "r":
				ImageIcon imagemInexistente = new ImageIcon("essa imagem nao existe");
	            setIcon(imagemInexistente);
	            return;
			case "atualizar":
				ImageIcon atualizada = new ImageIcon(sub.toString());
				setIcon(atualizada);
				locked = false;
				return;
			}
				anima();
		}
}

	@Override
	public void setSubject(ActorSubjectView sub) {
		this.sub = sub;
		
	}
	
	public void anima() {
		Metronomo metro = new Metronomo(10, 33);
		metro.addActionListener(this);
		metro.start();

	}

	@Override
	public boolean getLocked() {
		return locked;
	}
	
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	public void setNivelView(NivelView nivel) {
		this.nivelView = nivel;
	}
}