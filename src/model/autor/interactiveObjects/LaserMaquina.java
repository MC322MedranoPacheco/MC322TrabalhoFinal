package model.autor.interactiveObjects;

import java.awt.Container;
import java.util.ArrayList;

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
		while(!achou && iaction.getCelula(agora, sala) != null) {
			if(iaction.getCelula(agora, sala).getActor() == null) {
				System.out.println("Tcharammm");
				LaserFeixo laser = new LaserFeixo(agora.getX(), agora.getY(), sala, iaction);
				Actor ator = laser;
				iaction.getCelula(agora, sala).setActor(ator);
				subjectsCells.add(iaction.getCelula(agora, sala));
				lasersPositions.add(agora.clone());
				iaction.getCelula(agora, sala).registrar(this);
			}
			//else if(iaction.getCelula(agora, sala).getActor().getForca() == 0) {
				//LaserFeixoDuplo laser = new LaserFeixoDuplo(posicaoAtual.getX(), posicaoAtual.getY(), sala, iaction);
				//subjects.add(iaction.getCelula(agora, sala));
				//iaction.getCelula(agora, sala).registrar(this);
			//}
			else {
				achou = true;
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
		System.out.println("Entroudakhgdhadvjadjakvdkavd");
		boolean interseccao = true;
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
		
		while(!achou && iaction.getCelula(proximoLaser(agora), sala) != null) {
			agora = proximoLaser(agora);
			if(iaction.getCelula((agora), sala).getActor() == null) {
				
				System.out.println("Tcharammm");
				System.out.println(agora);
				LaserFeixo laser = new LaserFeixo(agora.getX(), agora.getY(), sala, iaction);
				Actor ator = laser;
				iaction.getCelula(agora, sala).setActor(ator);
				subjectsCells.add(iaction.getCelula(agora, sala));
				lasersPositions.add(agora.clone());
				iaction.getCelula(agora, sala).registrar(this);
				iaction.addImage(sala, agora);
			}
			else if(iaction.getCelula((agora), sala).getActor().getForca() == 0) {
				System.out.println("ENtoru onde n devia");
			}
			else {
				System.out.println(iaction.getCelula(agora, sala).getActor());
				achou = true;
			}
		}
	}
	
	
	private void desligar() {
		
		System.out.println("Desligou");
		for(Posicao pos : lasersPositions) {
			if(!iaction.getCelula(pos, sala).getOcupado() && iaction.getCelula(pos, sala).getActor() != null) {
				System.out.println("tirou" + pos);
				iaction.getCelula(pos, sala).remover(true);
				iaction.getCelula(pos,sala).excluirRegistro(this);
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
		return LaserMaquina.class.getResource(".").getPath() + "laserMaquina.png";
	}
	
	
}