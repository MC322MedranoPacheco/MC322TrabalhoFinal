package utilidades;

public class Posicao {
	
	int x;
	int y;
	
	public Posicao(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public Posicao clone() {
		Posicao posicao = new Posicao(this.x, this.y);
		return posicao;
	}
	
	public int getY() {
		return y;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public String toString() {
		return "Posicao: " + x + " " + y;
	}
	
	
}
