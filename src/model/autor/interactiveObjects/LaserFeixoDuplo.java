package model.autor.interactiveObjects;

import model.nivel.IAction;
import utilidades.Subject;

public class LaserFeixoDuplo extends LaserFeixo{
	private int segundaDirecao;
	
	public LaserFeixoDuplo(int x, int y, int sala, IAction iaction, int direcao, int segundaDirecao) {
		super(x, y, sala, iaction, direcao);
		this.segundaDirecao = segundaDirecao;
		resistencia = 1;
	}
	
	@Override
	public String toString() {
		
		if(direcao >= 2 && segundaDirecao >=2) 
			return LaserFeixoDuplo.class.getResource(".").getPath() + "laserRedHorizontal.png";
		else if(direcao <= 1 && segundaDirecao <= 1) 
			return LaserFeixoDuplo.class.getResource(".").getPath() + "laserRedVertical.png";
		else {
			return LaserFeixo.class.getResource(".").getPath() + "laserRedCruzado.png";
		}
	}
	
	public int getSegundaDirecao() {
		return segundaDirecao;
	}

}
