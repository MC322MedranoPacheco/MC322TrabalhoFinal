package model.autor.interactiveObjects;

import java.util.ArrayList;

import model.autor.Actor;
import model.autor.IVivo;
import model.nivel.IAction;
import utilidades.Observer;
import utilidades.Posicao;
import utilidades.Subject;

public class LaserMaquina extends Actor implements Subject, Observer{
	private int direcao;
	private ArrayList<Observer> observers;
	private boolean ativo;
	private boolean changed;
	private final Object MUTEX = new Object();
	private Subject frente;
 
	public LaserMaquina(int x, int y, int direcao, IAction iaction) { // fazer feixo adapter pra reduzir repeticao de codigo :) 
		super(x, y, iaction);
		this.direcao = direcao;
		this.observers = new ArrayList<>();
		this.forca = 1;
		this.resistencia = 10000;
		gerarFeixo(direcao);
		Subject[] sub = new Subject[1];
		sub[0] = iaction.getCelula(posicaoAtual, sala);
		setSubejects(sub);
		iaction.getCelula(posicaoAtual, sala).registrar(this);
	}
	
	

	@Override
	public boolean acao(String comando, IVivo vivo) {
		return false;
	}

	@Override
	public boolean acao(Posicao destino, IVivo vivo) {
		return false;
	}




	@Override
	public void registrar(Observer obj) {
		if(obj == null) throw new NullPointerException("Null Observer");
		synchronized(MUTEX) {
			if(!observers.contains(obj))observers.add(obj);
		}
		
	}



	@Override
	public void excluirRegistro(Observer obj) {
		synchronized(MUTEX) {
			observers.remove(obj);
		}
	}



	@Override
	public void notificarObservadores() {
		ArrayList<Observer> observersLocal = null;
		
		synchronized(MUTEX) {
			if(!changed)
				return;
			observersLocal = new ArrayList<>(this.observers);
			this.changed = false;
		}
		for(Observer obj : this.observers) {
			obj.update();
		}
		
	}



	@Override
	public Object getUpdate(Observer obj) {
		return this.ativo;
	}
	
	public void ligarDesligar() {
		if(this.ativo)
			this.ativo = false;
		else 
			this.ativo = true;
		this.changed = true;
		notificarObservadores();
	}

	public void gerarFeixo(int direcao) {
		Posicao pos;
		switch(direcao){
		case 0:
			pos = new Posicao(posicaoAtual.getX()+1,posicaoAtual.getY());
			break;
		case 1:
			pos = new Posicao(posicaoAtual.getX()-1,posicaoAtual.getY());
			break;
		case 2:
			pos = new Posicao(posicaoAtual.getX(),posicaoAtual.getY()+1);
			break;
		case 3:
			pos = new Posicao(posicaoAtual.getX(),posicaoAtual.getY()-1);
			break;
		default:
			pos = null;
		}
		if(iaction.getCelula(pos, this.sala)!=null && iaction.getCelula(pos, this.sala).getActor()  == null) {
			LaserFeixo feixo = new LaserFeixo(pos.getX(), pos.getY(), this.direcao, this.sala, this.iaction);															
			feixo.connect(iaction);
			iaction.getCelula(pos,this.sala).setActor(feixo);
			registrar(feixo);
			feixo.setSubject(this);
		}
		else if(iaction.getCelula(pos, this.sala)!=null && iaction.getCelula(pos, this.sala).getActor().getResistencia() == 0) {
			LaserFeixoDuplo feixoDuplo =  new LaserFeixoDuplo(pos.getX(), pos.getY(), this.direcao,
				((LaserFeixo)iaction.getCelula(pos, this.sala).getActor()).getDirecao(), this.sala, this.iaction);
			iaction.getCelula(pos, sala).remover();
			feixoDuplo.connect(iaction);
			iaction.getCelula(pos, sala).setActor(feixoDuplo);
			registrar(feixoDuplo);
			feixoDuplo.setSegundoSubject(this);
		}
	}

	
	@Override
	public String toString() {
		return LaserMaquina.class.getResource(".").getPath() + "laserUp.png";
	}
	
	@Override
	public void update() {
		if(!(boolean) frente.getUpdate(this)) {  
			gerarFeixo(direcao);
		}
		
	}



	@Override
	public void setSubejects(Subject[] sub) {
		frente = sub[0];
		
	}



	@Override
	public void setVivo(boolean vivo) {
	}



	@Override
	public boolean getVivo() {
		return false;
	}

	
}
