package model.autor.interactiveObjects;

import utilidades.Subject;

public class LaserFeixoDuplo extends LaserFeixo{
	private int segundaDirecao;
	private Subject segundoAnterior;
	private Subject segundoProximo;

	public LaserFeixoDuplo(int x, int y, int direcao,int segundaDirecao, int sala) {
		super(x, y, direcao, sala);
		gerarFeixo(segundaDirecao);
	}
	
	
	private void autodestruir(int direcao) {
		iaction.getCelula(posicaoAtual, sala).remover(); // destroi de um lado constroi o feixe de outro
		
	}

	@Override
	public void setSubejects(Subject[] sub) {
		super.setSubejects(sub);
		segundoAnterior = sub[2];
		segundoProximo = sub[3];
	}
	
	@Override
	public void update() {
		if((boolean) anterior.getUpdate(this)) {
			autodestruir(direcao);
		}
		if((boolean) proximo.getUpdate(this)) {
			gerarFeixo(direcao);
		}
		if((boolean) segundoAnterior.getUpdate(this)) {
			autodestruir(segundaDirecao);
		}
		if((boolean) segundoProximo.getUpdate(this)){
			gerarFeixo(segundaDirecao);
		}
	}
	

}
