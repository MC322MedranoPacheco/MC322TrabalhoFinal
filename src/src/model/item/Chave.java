package model.item;

public class Chave extends Item{

	public Chave(int x, int y, String code) {
		super(x, y, code);
		
	}

	public String toString() {
		return Chave.class.getResource(".").getPath() + "goldenKey.png";
	}
}
