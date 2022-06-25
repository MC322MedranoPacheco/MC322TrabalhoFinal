package model.autor.interactiveObjects;

import model.autor.Actor;
import model.autor.IActor;
import model.autor.IVivo;
import model.nivel.IAction;
import utilidades.Observer;
import utilidades.Posicao;
import utilidades.Subject;

public class LaserFeixo extends Actor implements Observer{
	protected int direcao;
	protected Subject anterior;
	protected Subject proximo;
	
	

	public LaserFeixo(int x, int y, int direcao, int sala, IAction iaction) {
		super(x,y, iaction);
		this.direcao = direcao;
		this.sala = sala;
		Subject[] subs = new Subject[4];
		subs[0] = iaction.getCelula(getPosAnterior(posicaoAtual, direcao), sala);// e se for o primeiro ?
		subs[1] = iaction.getCelula(getPosAnterior(posicaoAtual, direcao), sala);
		if(iaction.getCelula(getPosProxima(posicaoAtual, direcao), this.sala)!= null)
			iaction.getCelula(getPosProxima(posicaoAtual, direcao), this.sala).registrar(this);
		iaction.getCelula(getPosAnterior(posicaoAtual, direcao), this.sala).registrar(this);
		setSubejects(subs);
		forca = 0;
		resistencia =0;
		gerarFeixo(direcao);
		
		
	}

	@Override
	public boolean acao(String comando, IVivo vivo) {
		return false;
	}

	@Override
	public boolean acao(Posicao destino, IVivo vivo) {
		vivo.setVivo(false);
		return true;
	}
	

	public int getDirecao() {
		return direcao;
	}
	
	
	@Override
	public String toString() {
		return LaserFeixo.class.getResource(".").getPath() + "laserRedVertical.png";
	}

	@Override
	public void update() {
		if((boolean) anterior.getUpdate(this)) {
			autodestruir(this.direcao);
		}
		if(!(boolean) proximo.getUpdate(this)) {  
			gerarFeixo(direcao);
		}
	}
	
	
	
	public Posicao getPosAnterior(Posicao posAtual, int direcao) {
		switch(direcao) {
		case 0:
			return getPosProxima(posAtual, 1);
		case 1:
			return getPosProxima(posAtual, 0);
		case 2:
			return getPosProxima(posAtual, 3);
		case 3:
			return getPosProxima(posAtual, 2);
		default:
			return null;
		}
	}
	
	public Posicao getPosProxima(Posicao posAtual,int direcao) {
		Posicao pos;
		switch(direcao){
		case 0:
			pos = new Posicao(posAtual.getX()+1,posAtual.getY());
			break;
		case 1:
			pos = new Posicao(posAtual.getX()-1,posAtual.getY());
			break;
		case 2:
			pos = new Posicao(posAtual.getX(), posAtual.getY()+1);
			break;
		case 3:
			pos = new Posicao(posAtual.getX(),posAtual.getY()-1);
			break;
		default:
			pos = null;
		}
		return pos;
	}

	protected void gerarFeixo(int direcao) { // isso podia entrar no Laser Maquina tambem
		Posicao pos;
		switch(direcao){
		case 0:
			pos = new Posicao(posicaoAtual.getX()+1,posicaoAtual.getY());
			break;
		case 1:
			pos = new Posicao(posicaoAtual.getX()-1,posicaoAtual.getY());
			break;
		case 2:
			pos = new Posicao(posicaoAtual.getX(), posicaoAtual.getY()+1);
			break;
		case 3:
			pos = new Posicao(posicaoAtual.getX(),posicaoAtual.getY()-1);
			break;
		default:
			pos = null;
		}
		if(iaction.getCelula(pos, this.sala)!=null && iaction.getCelula(pos, this.sala).getActor()  == null) {
			IActor feixo = new LaserFeixo(pos.getX(), pos.getY(), direcao, sala, this.iaction);															
			feixo.connect(iaction);
			iaction.getCelula(pos,this.sala).setActor(feixo);
		}
		else if(iaction.getCelula(pos, this.sala)!=null && iaction.getCelula(pos, this.sala).getActor().getResistencia() == 0) {
	
			IActor feixoDuplo =  new LaserFeixoDuplo(pos.getX(), pos.getY(), direcao,
				((LaserFeixo) iaction.getCelula(pos, this.sala).getActor()).getDirecao(), this.sala, this.iaction);
			iaction.getCelula(pos, sala).remover(true);
			feixoDuplo.connect(iaction);
			iaction.getCelula(pos, sala).setActor(feixoDuplo); //falta implementar a conecao com o observer
			
		}	
	}

	public void autodestruir(int direcao) {
		anterior.excluirRegistro(this);
		proximo.excluirRegistro(this);
		iaction.getCelula(posicaoAtual, sala).remover(true);
	}

	@Override
	public void setSubejects(Subject[] sub) {
		anterior = sub[0];
		proximo = sub[1];
	}
	
	public void setSubject(Subject sub) {
		anterior = sub;
	}

	@Override
	public void setVivo(boolean vivo) {
	}

	@Override
	public boolean getVivo() {
		return false;
	}
	
	



}
