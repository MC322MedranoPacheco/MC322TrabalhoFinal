package model.autor.interactiveObjects;

import java.util.ArrayList;

import exceptions.InvalidParameterException;
import model.autor.Actor;
import model.autor.ICommand;
import model.autor.IVivo;
import model.item.Item;
import model.nivel.IAction;
import utilidades.Observer;
import utilidades.Posicao;
import utilidades.Subject;

public class LaserMaquina extends Actor implements Observer{
	private int direcao;
	private boolean ativo;
	private ArrayList<Subject> subjectsCells;
	private ArrayList<Subject> subjectsNonCells;
	private ArrayList<Posicao> lasersPositions;
	private boolean estadoDeLigacao;
	
	
	public LaserMaquina(int x, int y, int direcao, IAction iaction) { // fazer feixo adapter pra reduzir repeticao de codigo :) 
		super(x, y, iaction);
		subjectsCells = new ArrayList<>();
		subjectsNonCells = new ArrayList<>();
		lasersPositions = new ArrayList<>();
		forca =1 ;
		resistencia = 100000;
		estadoDeLigacao = true;
		this.direcao = direcao;
		boolean achou = false;
		Posicao agora = posicaoAtual;
		while(!achou && iaction.getCelula(agora, sala) != null ) {
			if(iaction.getCelula(agora, sala).getActor() == null) {
				System.out.println("Tcharammm");
				LaserFeixo laser = new LaserFeixo(agora.getX(), agora.getY(), sala, iaction, direcao);
				Actor ator = laser;
				iaction.getCelula(agora, sala).setActor(ator);
				subjectsCells.add(iaction.getCelula(agora, sala));
				lasersPositions.add(agora.clone());
				iaction.getCelula(agora, sala).registrar(this);
			}
			else if(iaction.getCelula((agora), sala).getActor().getForca() == 0) {
				int segundaDirecao = ((LaserFeixo)iaction.getCelula((agora), sala).remover(true)).getDirecao();
				LaserFeixoDuplo laser = new LaserFeixoDuplo(agora.getX(), agora.getY(), sala, iaction, direcao, segundaDirecao);
				Actor ator = laser;
				iaction.getCelula(agora, sala).setActor(ator);
				subjectsCells.add(iaction.getCelula(agora, sala));
				lasersPositions.add(agora.clone());
				iaction.getCelula(agora, sala).registrar(this);

			}
			else {
				achou = true;
				subjectsCells.add(iaction.getCelula(agora, sala));
				lasersPositions.add(agora.clone());
				iaction.getCelula(agora, sala).registrar(this);
			}
			agora = proximoLaser(agora);
		}
	}
	@Override
	public boolean acao(String comando, IVivo vivo) {
		return false;
	}
	
	
	@Override
	public void setSubejects(Subject[] sub) {
		for(int i =0; i< sub.length; i++) {
			if(sub[i] != null)
				subjectsNonCells.add(sub[i]);
		}
	}
	
	
	
	public ArrayList<Subject> getSubjects() {
		return subjectsNonCells;
	}
	
	
	
	@Override
	public void update() {
		for(Subject sub : subjectsNonCells) {
			if((boolean) sub.getUpdate(this)) {
				estadoDeLigacao = false;
			}
		}
		if(estadoDeLigacao) {
			desligar();
			ligar();
			
		}
		else {
			desligar();
		}
	}
	
	private Posicao proximoLaser(Posicao agora) {
		Posicao proximo = agora.clone();
		switch(direcao) {
		case 0:
			proximo.setY(proximo.getY()-1);
			break;
		
		case 1:
			proximo.setY(proximo.getY()+1);
			break;
		
		case 2:
			proximo.setX(proximo.getX()-1);
			break;
		case 3:
			proximo.setX(proximo.getX()+1);
			break;
		}
		return proximo;
	}
	
	
	private void ligar() {
		boolean achou = false;
		Posicao agora = posicaoAtual;
		
		while(!achou && (iaction.getCelula(proximoLaser(agora), sala) != null )) {
			agora = proximoLaser(agora);
			if(iaction.getCelula((agora), sala).getActor() == null) {
				System.out.println(agora);
				LaserFeixo laser = new LaserFeixo(agora.getX(), agora.getY(), sala, iaction, direcao);
				Actor ator = laser;
				iaction.getCelula(agora, sala).setActor(ator);
				subjectsCells.add(iaction.getCelula(agora, sala));
				lasersPositions.add(agora.clone());
				iaction.getCelula(agora, sala).registrar(this);
				iaction.addImage(sala, agora);
			}
			else if(iaction.getCelula((agora), sala).getActor().getForca() == 0) {
				int segundaDirecao = ((LaserFeixo)iaction.getCelula((agora), sala).remover(true)).getDirecao();
				LaserFeixoDuplo laser = new LaserFeixoDuplo(agora.getX(), agora.getY(), sala, iaction, direcao, segundaDirecao);
				Actor ator = laser;
				iaction.getCelula(agora, sala).setActor(ator);
				subjectsCells.add(iaction.getCelula(agora, sala));
				lasersPositions.add(agora.clone());
				iaction.getCelula(agora, sala).registrar(this);
				iaction.addImage(sala, agora);
				
			}
			else {
				achou = true;
			}
		}
	}
	
	
	private void desligar() {
		
		System.out.println("Desligou");
		for(Posicao pos : lasersPositions) {
			if(!iaction.getCelula(pos, sala).getOcupado() && iaction.getCelula(pos, sala).getActor() != null) {
				iaction.getCelula(pos,sala).excluirRegistro(this);
				LaserFeixoDuplo laser;
				LaserFeixo feixo;
				if(iaction.getCelula(pos, sala).getActor().getResistencia() == 1) {
					 laser = (LaserFeixoDuplo) iaction.getCelula(pos, sala).remover(true);
					 if(laser.getDirecao() == direcao) {
						 feixo = new LaserFeixo(pos.getX(), pos.getY(),sala, iaction, laser.getSegundaDirecao());
						 iaction.getCelula(pos, sala).setActor(feixo);
						 iaction.addImage(sala, pos);
					 }
					 else {
						 feixo = new LaserFeixo(pos.getX(), pos.getY(),sala, iaction, laser.getDirecao());
						 iaction.getCelula(pos, sala).setActor(feixo);
						 iaction.addImage(sala, pos);
					 }
				}
				else {
					iaction.getCelula(pos, sala).remover(true);
				}
			}
			else if(iaction.getCelula(pos, sala).getOcupado()) {
				iaction.getCelula(pos, sala).getActor().setVivo(false);
			}
		}
		subjectsCells.clear();
		lasersPositions.clear();
		
		
	}
	
	
	
	@Override
	public boolean acao(Posicao destino, ICommand receiver, ICommand maker) {
		return false;
	}
	@Override
	public ArrayList<Item> getInventario() {
		return null;
	}
	@Override
	public void setVivo(boolean vivo) {
	}
	@Override
	public boolean getVivo() {
		return false;
	}
	
	@Override
	public String toString() {
		try {
		switch(direcao) {
		case 0:
			return LaserMaquina.class.getResource(".").getPath() + "laserUp.png";
		case 1:
			return LaserMaquina.class.getResource(".").getPath() + "laserDown.png";
		
		case 2:
			return LaserMaquina.class.getResource(".").getPath() + "laserLeft.png";
		case 3:
			return LaserMaquina.class.getResource(".").getPath() + "laserRight.png";
		
		default:
			throw new InvalidParameterException("direcao invalida");
		}
		}
		catch(InvalidParameterException e) {
			e.getMessage();
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Posicao getPosicaoAnterior() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean interact(ArrayList<Item> inventario) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public int getForca() {
		return 2;
	}
	
}