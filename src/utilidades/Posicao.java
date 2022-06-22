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
	
	public static Posicao direcao(Posicao anterior, Posicao proxima) {
		Posicao resultado;
		if(anterior.getX() < proxima.getX()) 
			resultado = new Posicao(proxima.getX()+1, proxima.getY());
		else if(anterior.getX() > proxima.getX()) 
			resultado = new Posicao(proxima.getX()-1, proxima.getY());
		else if(anterior.getY() < proxima.getY())
			resultado = new Posicao(proxima.getX(), proxima.getY() + 1);
		else if(anterior.getY() > proxima.getY())
			resultado = new Posicao(proxima.getX(), proxima.getY() - 1);
		else {
			resultado = null; // nao pode entrar aqui eh erro que deve ser tratado
		}
		return resultado;
	}
	
	
}
